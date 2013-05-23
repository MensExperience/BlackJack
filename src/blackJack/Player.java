package blackJack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    
    private boolean isHit;
    
    private int calcResult;
    
    private List<Card> cardList = new ArrayList<Card>();
    
    private boolean isBurst;
    
    private boolean isBlackJack;
    

    protected Player(){
        isHit = false;
        calcResult = 0;
        isBurst = false;
        isBlackJack = false;
    }
    
    protected void calcCard(){
        int calcResult = 0;
        
        for (Card card : this.cardList) {
            int num = card.getNumber();
            switch (num) {
            case 1:
                if (21 - calcResult > 11) {
                    calcResult += 11;
                } else {
                    calcResult += 1;
                }
                break;
            case 2-10:
                calcResult += num;
                break;
            case 11-13:
                calcResult += 10;
                break;

            default:
                break;
            }
        }
        
        setCalcResult(calcResult);
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

    /**
     * @param cardList セットする cardList
     */
    public void setCardListAndCalc(List<Card> cardList) {
        this.cardList = cardList;
        calcCard();
    }
    
    public void addCardAndCalc(Card card){
        this.cardList.add(card);
        calcCard();
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
     * @return isBurst
     */
    public boolean isBurst() {
        return isBurst;
    }

    /**
     * @param isBurst セットする isBurst
     */
    public void setBurst(boolean isBurst) {
        this.isBurst = isBurst;
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
