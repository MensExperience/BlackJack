package blackJack;

public class Card{
    //マーク
    private String mark;
    
    //番号
    private Integer number;
    
    //表示名
    private String name;

    protected Card() {
    }
    
    protected Card(String mark, Integer number){
        this.mark = mark;
        this.number = number;
        switch (number) {
        case 1:
            this.name = "A";
            break;
        case 2-10:
            this.name = number.toString();
            break;
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
    
    /**
     * @return mark
     */
    public String getMark() {
        return mark;
    }

    /**
     * @param mark セットする mark
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
     * @param number セットする number
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
     * @param name セットする name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
