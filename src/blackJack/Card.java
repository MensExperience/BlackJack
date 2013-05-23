package blackJack;

/**
 * トランプのカード一枚を表すクラス
 */
public class Card {

    /** トランプのマーク */
    private String mark;

    /** カード番号 */
    private Integer number;

    /** カード表示名 */
    private String name;

    protected Card() {
    }

    /**
     * マークと番号を指定してカードを作成する 番号に応じて表示名をセットする
     * 
     * @param mark
     * @param number
     */
    protected Card(String mark, Integer number) {
        this.mark = mark;
        this.number = number;

        if (number == 1) {
            this.name = "A";
        } else if (number >= 2 && number <= 10) {
            this.name = number.toString();
        } else {
            switch (number) {
            case 11:
                this.name = "J";
                break;
            case 12:
                this.name = "Q";
                break;
            case 13:
                this.name = "K";
                break;
            default:
                break;
            }
        }
    }

    /**
     * @return mark
     */
    public String getMark() {
        return mark;
    }

    /**
     * @param mark
     *            セットする mark
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * @return number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number
     *            セットする number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            セットする name
     */
    public void setName(String name) {
        this.name = name;
    }

}
