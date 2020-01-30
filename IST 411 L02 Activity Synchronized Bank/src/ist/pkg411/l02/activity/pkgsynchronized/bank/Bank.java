/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg411.l02.activity.pkgsynchronized.bank;

import java.util.Arrays;

/**
 *
 * @author cjani
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
            wait();
         System.out.print(Thread.currentThread());
         accounts[from] -= amount;
         System.out.printf(" %10.2f from %d to %d", amount, from, to);
         accounts[to] += amount;
         System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
         notify();  // using notify instead of notifyall() causes the program to Hang
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