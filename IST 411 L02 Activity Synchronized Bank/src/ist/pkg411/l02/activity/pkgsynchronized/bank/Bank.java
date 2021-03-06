/* 
Project: IST 411 - Week 3 Solo Work - Synchronization
Purpose Details: Fix a deadlocked program using synchronization for multi-threading
Course: IST 411
Author: Sam Janvey
Date Developed: 2/2/20
Last Date Changed: 2/2/20
Revision: 
*/
package ist.pkg411.l02.activity.pkgsynchronized.bank;

import java.util.Arrays;

/**
 *
 * @author sjanv
 */
public class Bank 
   {
      private final double[] accounts;

      /**
       * Constructs the bank.
       * @param n the number of accounts
       * @param initialBalance the initial balance for each account
       */
      public Bank(int n, double initialBalance)
      {
         accounts = new double[n];
         Arrays.fill(accounts, initialBalance);
      }

      /**
       * Transfers money from one account to another.
       * @param from the account to transfer from
       * @param to the account to transfer to
       * @param amount the amount to transfer
       */
      public synchronized void transfer(int from, int to, double amount) throws InterruptedException
      {
         while (accounts[from] < amount)
             // wait() will handle the locking and conditional evaluation
             // until the while condition evaluates to false
            wait();
         
         // when condition evaluates to false, thread will continue to execute
         System.out.print(Thread.currentThread());
         accounts[from] -= amount;
         System.out.printf(" %10.2f from %d to %d", amount, from, to);
         accounts[to] += amount;
         System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
         
         // necessary for program to run correctly
         notifyAll(); // notify all other threads to check for balance updates
      }


     /**
       * Gets the sum of all account balances.
       * @return the total balance
       */
     public synchronized double getTotalBalance()
      {
         double sum = 0;

         for (double a : accounts)
            sum += a;

         return sum;
      }

      /**
       * Gets the number of accounts in the bank.
       * @return the number of accounts
       */
      public int size()
      {
         return accounts.length;
      }
   }