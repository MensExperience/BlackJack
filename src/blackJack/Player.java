package blackJack;

import java.util.ArrayList;
import java.util.List;

/**
 * ブラックジャックの参加者を表すクラス
 */
public class Player {
    
    /** ヒットする場合はtrue */
    private boolean isHit;
    
    /** 合計の計算結果 */
    private int calcResult;
    
    /** 引いたカードのリスト */
    private List<Card> cardList = new ArrayList<Card>();
    
    /** ブラックジャックならtrue */
    private boolean isBlackJack;
    
    /** 
     * 初期値をセットしてPlayerオブジェクト作成
     */
    protected Player(){
        isHit = false;
        calcResult = 0;
        isBlackJack = false;
    }
    
    /**
     * 引いたカードから合計値を計算してセットする
     * @param card
     */
    protected void calcCard(Card card){
        int num = card.getNumber();
        
        for (int i=1; i<=13; i++) {
            if (num == 1) {
                if (21 - this.calcResult > 11) {
                    this.calcResult += 11;
                } else {
                    this.calcResult += 1;
                }
                break;
            } else if (num >= 2 && num <= 10) {
                this.calcResult += num;
                break;
            } else {
                this.calcResult += 10;
                break;
            }
        }
    }
    
    /**
     * @return cardList
     */
    public List<Card> getCardList() {
        return cardList;
    }
    

    /**
     * @param cardList セットする cardList
     */
    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
    
    public void addCardAndCalc(Card card){
        this.cardList.add(card);
        calcCard(card);
    }
    
    /**
     * @return isHit
     */
    public boolean isHit() {
        return isHit;
    }
    /**
     * @param isHit セットする isHit
     */
    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }
    /**
     * @return calcResult
     */
    public int getCalcResult() {
        return calcResult;
    }
    /**
     * @param calcResult セットする calcResult
     */
    public void setCalcResult(int calcResult) {
        this.calcResult = calcResult;
    }

    /**
     * @return isBlackJack
     */
    public boolean isBlackJack() {
        return isBlackJack;
    }

    /**
     * @param isBlackJack セットする isBlackJack
     */
    public void setBlackJack(boolean isBlackJack) {
        this.isBlackJack = isBlackJack;
    }
}
