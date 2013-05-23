package blackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck{
    
    private List<Card> cardDeck = new ArrayList<Card>();
    
    protected Deck(){
        String[] markArray = {"ハート", "スペード", "ダイヤ", "クラブ"};

        //マークごとに1－13のカードを配る
        for (String mark : markArray) {
            for (int i =1; i<=13; i++) {
                cardDeck.add(new Card(mark, i));
            }
        }
    }
    
    //カードを配る
    protected Card dealCard(){
        
        int randomNum = new Random().nextInt(cardDeck.size());
        
        return cardDeck.get(randomNum);
    }
    
    //配ったカードを除く
    protected void deleteCard(final Card card){
        cardDeck.remove(card);
    }

}
