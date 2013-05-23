package blackJack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputConsole{
    
    public static Player player;
    public static Player dealer;
    public static Deck deck = new Deck();
    public static int turn = 0;
    public static boolean isEnd = false;
    
    /**
     * コンソールからの入力を取得する。
     * "H"(ヒット)、または"S"(スタンド)以外はエラーとし、
     * "H"または"S"が入力されるまで繰り返す。
     * @param args
     */
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = "";
        
        String firstDispStr = "プレーヤーのカード : ";
        
        List<Card> playerCardList = new ArrayList<Card>();
        List<Card> dealerCardList = new ArrayList<Card>();
        
        //カードを配る(ディーラー用とプレーヤー用が必要)
        for (int i=1; i<=2; i++) {
            Card playerCard = deck.dealCard();
            deck.deleteCard(playerCard);
            Card dealerCard = deck.dealCard();
            deck.deleteCard(dealerCard);
            playerCardList.add(playerCard);
            dealerCardList.add(dealerCard);
            
            firstDispStr += playerCard.getName() + " ";
        }
        
        System.out.println(firstDispStr);
        
        player.setCardListAndCalc(playerCardList);
        dealer.setCardListAndCalc(dealerCardList);
        
        //ターンを進める
        
        
    }
    
    //ターンを進める
    public static void dealCard(){
        //ディーラーの動き
        if (dealer.isHit()) {
            Card card = deck.dealCard();
            deck.deleteCard(card);
            dealer.addCardAndCalc(card);
        }
        
        if (dealer.getCalcResult() > 21) {
            dealer.setBurst(true);
            isEnd = true;
        } else if (dealer.getCalcResult() == 21) {
            if (turn == 0) {
                dealer.setBlackJack(true);
            }
            isEnd = true;
            
        } else if (dealer.getCalcResult() >= 17) {
            dealer.setHit(false);
        } else {
            dealer.setHit(true);
        }
        
        //プレーヤーの動き
        if (player.isHit()) {
            Card card = deck.dealCard();
            deck.deleteCard(card);
            player.addCardAndCalc(card);
            System.out.println("引いたカード : " + card.getName());
        }
        
        if (player.getCalcResult() > 21) {
            player.setBurst(true);
            isEnd = true;
        } else if (player.getCalcResult() == 21) {
            if (turn == 0) {
                player.setBlackJack(true);
            }
            
            isEnd = true;
        } else {
            player.setHit(hitOrStand());
        }
        
        if (!dealer.isHit() && !player.isHit()) {
            isEnd = true;
        }
        
        //勝敗判定
        if (isEnd) {
            
        } else {
            turn++;
            dealCard();
        }
    }
    
    
    
    /** ヒット(H)、またはスタンド(S)を入力する */
    public static boolean hitOrStand() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        boolean isDealCard = false;

        do {
            System.out.println("[H]または[S]を入力してください");
            try {
                str = br.readLine();
                if ("h".equals(str) || "H".equals(str)) {
                    isDealCard = true;
                } else if ("s".equals(str) || "S".equals(str)) {
                    isDealCard = false;
                } else {
                    str = "";
                }
            } catch(IOException e) {
                System.out.println("入力エラー:" + e.getMessage());
                str = "";
            }
        } while ("".equals(str));

        return isDealCard;
    }
    
    public static void judge(){
        //バーストしているか判定
        if (player.isBurst() && dealer.isBurst()) {
            System.out.println("引き分けです");
            return;
        } else if (dealer.isBurst()) {
            System.out.println("プレイヤーの勝ちです");
            System.out.println("プレイヤー : " + player.getCalcResult() + " / ディーラー : " + dealer.getCalcResult());
        } else if (player.isBurst()) {
            System.out.println("ディーラーの勝ちです");
            System.out.println("プレイヤー : " + player.getCalcResult() + " / ディーラー : " + dealer.getCalcResult());
        } else if (player.getCalcResult() == 21 && dealer.getCalcResult() == 21) {
            System.out.println("引き分けです");
            return;
        } else if (player.getCalcResult() == 21) {
            System.out.println("プレイヤーの勝ちです");
            System.out.println("プレイヤー : " + player.getCalcResult() + " / ディーラー : " + dealer.getCalcResult());
        } else if (dealer.getCalcResult() == 21) {
            System.out.println("ディーラーの勝ちです");
            System.out.println("プレイヤー : " + player.getCalcResult() + " / ディーラー : " + dealer.getCalcResult());
        } else if (player.getCalcResult() > dealer.getCalcResult()) {
            System.out.println("プレイヤーの勝ちです");
            System.out.println("プレイヤー : " + player.getCalcResult() + " / ディーラー : " + dealer.getCalcResult());
        } else if (player.getCalcResult() < dealer.getCalcResult()) {
            System.out.println("ディーラーの勝ちです");
            System.out.println("プレイヤー : " + player.getCalcResult() + " / ディーラー : " + dealer.getCalcResult());
        } else {
            System.out.println("引き分けです");
            return;
        }
        
    }

}
