package me.whiteship.inflearnthejavatest.Money;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Money {

    protected  int amount;
    protected  String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money() {

    }

    public static Money dollor(int i) {
        return new Money(i ,"USD");
    }

    public static Money franc(int i) {
        return new Money(i , "CHF");
    }

    public Money times(int i){
        return new Money(amount * i , currency);
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Money money = (Money) o;
        return amount == money.amount && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }


    public Money plus(Money dollor) {
        return null;
    }
}
