import java.util.Objects;

public class Card {
    // here the cards will be initialized
    int number;
    String color;
    String card;
    boolean IsSpecialized;
    boolean isDrawTwo = false;
    boolean isReverse = false;
    boolean isSkip = false;
    boolean isWildDrawFour = false;
    boolean isWild = false;

    Card(int number,String color){
       this.number = number;
       this.color = color;
       card = number+"-"+color;
       IsSpecialized = false;
    }
    Card(String specialCard,String color){
        number = -1;
        IsSpecialized = true;
        this.color = color;
        card = specialCard+"-"+color;
       if(!Objects.equals(color, "Wild")) {
           switch (specialCard) {
               case "Draw two" -> isDrawTwo = true;
               case "Reverse" -> isReverse = true;
               case "Skip" -> isSkip = true;
           }
       }else{
           if(specialCard.equals("Wild Draw Four")){
               isWildDrawFour = true;
           }else{
               isWild = true;
           }
       }
    }

    public boolean CheckValidityOfCards(Card newcard){
        // check by number, color, speciality
        return (this.number!=-1 && this.number == newcard.number)||(this.color.equals(newcard.color))||(this.isDrawTwo && newcard.isDrawTwo)||(this.isReverse && newcard.isReverse)||(this.isSkip && newcard.isSkip)||newcard.isWildDrawFour||newcard.isWild;
    }
    public boolean equals(Card newcard){
        return this == newcard;
    }
}
