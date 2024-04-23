package br.com.gurodriguesdev.paymentsmicroservice.transaction;

import br.com.gurodriguesdev.paymentsmicroservice.authorization.AuthorizerService;
import br.com.gurodriguesdev.paymentsmicroservice.exception.InvalidTransactionException;
import br.com.gurodriguesdev.paymentsmicroservice.notification.NotificationService;
import br.com.gurodriguesdev.paymentsmicroservice.wallet.WalletRepository;
import br.com.gurodriguesdev.paymentsmicroservice.wallet.WalletType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, AuthorizerService authorizerService, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction create(Transaction transaction) {
        validate(transaction);

        var newTransaction = transactionRepository.save(transaction);

        var walletPayer = walletRepository.findById(transaction.payer()).get();
        walletRepository.save(walletPayer.debit(transaction.value()));

        var walletPayee = walletRepository.findById(transaction.payee()).get();
        walletRepository.save(walletPayee.credit(transaction.value()));

        authorizerService.authorize(transaction);

        notificationService.notify(transaction);

        return newTransaction;
    }

    public void validate(Transaction transaction) {
        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payer())
                        .map(
                                payer -> payer.type() == WalletType.COMUM.getValue() &&
                                        payer.balance().compareTo(transaction.value()) >= 0 &&
                                        !payer.id().equals(transaction.payee()) ? true : null)
                        .orElseThrow(() -> new InvalidTransactionException(
                                "Invalid transaction - " + transaction)))
                .orElseThrow(() -> new InvalidTransactionException(
                        "Invalid transaction - " + transaction));
    }

    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
