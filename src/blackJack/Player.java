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
    protected Player() {
        isHit = false;
        calcResult = 0;
        isBlackJack = false;
    }

    /**
     * カードリストにAが含まれているか返す
     * 
     * @return Aが含まれているならtrue
     */
    protected boolean isContainA() {
        for (Card card : this.cardList) {
            if (card.getNumber() == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 引いたカードから合計値を計算してセットする
     * 
     * @param card
     */
    protected void calcCard(Card card) {
        int num = card.getNumber();

        if (num == 1) {
            if (21 - this.calcResult > 11) {
                this.calcResult += 11;
            } else {
                this.calcResult += 1;
            }
        } else if (num >= 2 && num <= 10) {
            this.calcResult += num;
        } else {
            this.calcResult += 10;
        }
    }

    /**
     * カードリストから合計値を計算してセットする
     * カードリストを並べ替えてAを最後に判定する
     * 
     * @param card
     */
    protected void calcCardList() {
        //合計値を初期化
        this.calcResult = 0;
        
        //Aの数を数える
        int countA = 0;
        
        for (Card card : this.cardList) {
            int num = card.getNumber();
            if (num == 1) {
                countA++;
            } else if (num >= 2 && num <= 10) {
                this.calcResult += num;
            } else {
                this.calcResult += 10;
            }
        }
        
        //Aの計算
        for (int i=0; i<countA; i++) {
            if (21 - this.calcResult > 11) {
                this.calcResult += 11;
            } else {
                this.calcResult += 1;
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
     * @param cardList
     *            セットする cardList
     */
    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    /**
     * 引いたカードをカードリストに追加し、 合計を計算する
     * 
     * @param card
     */
    public void addCardAndCalc(Card card) {
        this.cardList.add(card);
        if (isContainA()) {
            calcCardList();
        } else {
            calcCard(card);
        }
    }

    /**
     * @return isHit
     */
    public boolean isHit() {
        return isHit;
    }

    /**
     * @param isHit
     *            セットする isHit
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
     * @param calcResult
     *            セットする calcResult
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
     * @param isBlackJack
     *            セットする isBlackJack
     */
    public void setBlackJack(boolean isBlackJack) {
        this.isBlackJack = isBlackJack;
    }
}
