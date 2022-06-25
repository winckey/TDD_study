package me.whiteship.inflearnthejavatest.Money;

public class Dollar {
    public static int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    public void times(int i) {

        amount *=i;

    }
}
