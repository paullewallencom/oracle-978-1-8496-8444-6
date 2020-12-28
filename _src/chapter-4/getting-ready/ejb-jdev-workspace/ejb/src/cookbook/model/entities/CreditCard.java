package cookbook.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class CreditCard implements Serializable {
    public CreditCard() {
    }

    private String cardIssuer;
    private String cardNumber;
    private String cardholderName;
    private String expirationDate;
    private Long cardValidationCode;

    public void setCardIssuer(String cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    public String getCardIssuer() {
        return cardIssuer;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setCardValidationCode(Long cardValidationCode) {
        this.cardValidationCode = cardValidationCode;
    }

    public Long getCardValidationCode() {
        return cardValidationCode;
    }
}
