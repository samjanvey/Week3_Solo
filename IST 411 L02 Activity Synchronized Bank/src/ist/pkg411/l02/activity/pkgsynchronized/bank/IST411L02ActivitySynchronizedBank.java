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

/**
 *
 * @author sjanv
 */
public class IST411L02ActivitySynchronizedBank {

      public static final int NACCOUNTS = 5;
      public static final double INITIAL_BALANCE = 1000;
      public static final double MAX_AMOUNT = 1000;
      public static final int DELAY = 10;

      public static void main(String[] args)
      {
         Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
         for (int i = 0; i < NACCOUNTS; i++)
         {
            int fromAccount = i;
            Runnable r = () -> {
               try
               {
                  while (true)
                  {
                    int toAccount = (int) (bank.size() * Math.random());
                     double amount = MAX_AMOUNT * Math.random();
                     bank.transfer(fromAccount, toAccount, amount);
                     Thread.sleep((int) (DELAY * Math.random()));
                  }
               }
               catch (InterruptedException e)
               {
               }
            };
            Thread t = new Thread(r);
            t.start();
         }
      }
   }

