package com.example.LMSBackend.Service;

import java.time.LocalDate;
import java.util.List;
import com.example.LMSBackend.Enums.CardStatus;
import com.example.LMSBackend.Enums.TransactionStatus;
import com.example.LMSBackend.Models.Book;
import com.example.LMSBackend.Models.Card;
import com.example.LMSBackend.Models.Transaction;
import com.example.LMSBackend.Repository.BookRepository;
import com.example.LMSBackend.Repository.CardRepository;
import com.example.LMSBackend.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TransactionRepository transactionRepository;

    private int maxAllowedBooks = 5;
    private int finePerDay = 20;


    public String issueBook(int cardId, int bookId) throws Exception {
        Card card = cardRepository.findById(cardId).get();
        Book book = bookRepository.findById(bookId).get();

        Transaction transaction = new Transaction();
        transaction.setIssueOperation(true);
        transaction.setBook(book);
        transaction.setCard(card);

        if(book == null || book.isAvailable() == false){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is either unavailable or not present");
        }

        if(card == null || card.getCardStatus()== CardStatus.DEACTIVATED || card.getCardStatus()== CardStatus.BLOCKED || card.getCardStatus()== CardStatus.SUSPENDED)  {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is invalid");
        }

        if(card.getBooksIssued().size() >= maxAllowedBooks){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book limit has reached for this card");
        }

        card.getBooksIssued().add(book);
        book.setCard(card);
        book.setAvailable(false);
        book.getTransactions().add(transaction);

        bookRepository.save(book);
        cardRepository.save(card);

        transaction.setTransactionStatus(TransactionStatus.SUCCESSFULL);
        transactionRepository.save(transaction);

        return transaction.getTransactionId();
    }

    public Transaction returnBook(int cardId, int bookId) throws Exception{
        List<Transaction> transactionList = transactionRepository.findAll();
        Transaction transaction = null;

        for(Transaction newTransaction : transactionList){

            if(newTransaction.getCard().getId()==cardId && newTransaction.getBook().getId()==bookId && newTransaction.getTransactionStatus()==TransactionStatus.SUCCESSFULL && newTransaction.isIssueOperation()){
                transaction = newTransaction;
            }
        }

        Date issueDate = transaction.getTransactionDate();
        long timeIssueTime = System.currentTimeMillis() - issueDate.getTime();
        long noOfDaysPassed = TimeUnit.DAYS.convert(timeIssueTime, TimeUnit.MILLISECONDS);

        int fine = 0;
        if(noOfDaysPassed >  maxAllowedBooks) {
            fine = (int) ((noOfDaysPassed - maxAllowedBooks) * finePerDay);
        }

        Book book = transaction.getBook();
        book.setAvailable(true);
        book.setCard(null);
        bookRepository.save(book);

        Card card = cardRepository.findById(cardId).get();
        card.getBooksIssued().remove(book);
        cardRepository.save(card);

        Transaction trxn = new Transaction();
        trxn.setTransactionStatus(TransactionStatus.SUCCESSFULL);
        trxn.setBook(transaction.getBook());
        trxn.setCard(transaction.getCard());
        trxn.setIssueOperation(false);
        trxn.setFineAmount(fine);

        transactionRepository.save(trxn);

        return trxn;
    }
}
