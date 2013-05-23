package blackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** 
 * トランプ1組分を表すクラス
 */
public class Deck{
    
    /** トランプ1組分のカードリスト */
    private List<Card> cardDeck = new ArrayList<Card>();
    
    /**
     * 4つのマークそれぞれについて1－13のカードをセットする
     */
    protected Deck(){
        String[] markArray = {"ハート", "スペード", "ダイヤ", "クラブ"};

        //マークごとに1－13のカードを配る
        for (String mark : markArray) {
            for (int i =1; i<=13; i++) {
                cardDeck.add(new Card(mark, i));
            }
        }
    }
    
    /**
     * カードを一枚配る
     * 
     * @return Card 引いたカード
     */
    protected Card dealCard(){
        
        int randomNum = new Random().nextInt(cardDeck.size());
        
        return cardDeck.get(randomNum);
    }
    
    /**
     * 使用したカードを除く
     * 
     * @param card
     */
    protected void deleteCard(final Card card){
        cardDeck.remove(card);
    }

}
