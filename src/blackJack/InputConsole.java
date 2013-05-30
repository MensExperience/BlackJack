package blackJack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ブラックジャックを表すクラス
 */
public class InputConsole{
    
    private static Player player = new Player();
    private static Player dealer = new Player();
    private static Deck deck = new Deck();
    
    private static final String EVEN = "引き分けです";
    private static final String PLAYER_WIN = "プレーヤーの勝ち";
    private static final String DEALER_WIN = "ディーラーの勝ち";
    private static String resultStr = "";
    
    /**
     * ブラックジャックで遊ぶ
     */
    public static void main(String[] args) {
        
        //カードを配る
        startGame();
        
        //勝敗判定
        judge();
        
    }
    
    /**
     * ゲームを開始する
     * 最初の2枚を配った後プレーヤーの動きを呼び出す
     */
    private static void startGame(){
        String startStr = "プレーヤーのカード : ";
        
        //カードを配る(ディーラー用とプレーヤー用が必要)
        for (int i=1; i<=2; i++) {
            Card playerCard = deck.dealCard();
            player.addCardAndCalc(playerCard);
            deck.deleteCard(playerCard);
            
            Card dealerCard = deck.dealCard();
            dealer.addCardAndCalc(dealerCard);
            deck.deleteCard(dealerCard);
            
            startStr += playerCard.getMark() + "の" + playerCard.getName() + " ";
        }
        
        System.out.println(startStr);
        
        //プレーヤーから行動
        playerGame(true);
    }
    
    /** 
     * プレーヤーの挙動
     * 
     * ブラックジャックまたはバーストの場合を除き、
     * hitOrStand()結果をもとにplayerのヒットフラグをセットし、繰り返す
     */
    private static void playerGame(boolean isFirst) {
        
        //プレーヤーのヒットフラグがtrueの時カードを引く(初回は通らない)
        if (player.isHit()) {
            Card card = deck.dealCard();
            player.addCardAndCalc(card);
            deck.deleteCard(card);
            System.out.println("引いたカード : " + card.getMark() + "の" + card.getName());
        }
        
        /*
         * 結果が21以上・・・バーストで終了
         * 結果が21・・・・・目標達成なので終了、初期状態だった場合はブラックジャックフラグをセット
         * それ以外・・・・・入力値によってヒットフラグをセットする
         */
        if (player.getCalcResult() > 21) {
            player.setHit(false);
        } else if (player.getCalcResult() == 21) {
            if (player.getCardList().size() == 2) {
                player.setBlackJack(true);
            }
            player.setHit(false);
        } else {
            player.setHit(hitOrStand());
        }
        
        /*
         * 初回は必ずディーラーの動きを呼び出す
         * ディーラーのヒットフラグがtrueならdealerGame()
         * プレーヤーのヒットフラグがtrueならplayerGame()
         * ヒットフラグが両方falseになるまで再帰呼び出し
         */
        if (isFirst) {
            dealerGame();
        } else if (dealer.isHit()) {
            dealerGame();
        } else if (player.isHit()) {
            playerGame(false);
        }
    }
    
    /**
     * ディーラーの挙動
     * 
     * ブラックジャックまたはバーストの場合を除き、
     * 結果が17以上ならdealerのヒットフラグにfalseをセット
     * 結果が17未満ならdealerのヒットフラグにtrueをセットして繰り返す
     */
    private static void dealerGame() {
        
        //ディーラーのヒットフラグがtrueの時カードを引く(初回は通らない)
        if (dealer.isHit()) {
            Card card = deck.dealCard();
            dealer.addCardAndCalc(card);
            deck.deleteCard(card);
        }
        
        /*
         * 結果が21以上・・・バーストで終了
         * 結果が21・・・・・目標達成なので終了、初期状態だった場合はブラックジャックフラグをセット
         * 結果が17以上・・・ヒットフラグをfalseにして終了
         * 結果が17未満・・・ヒットフラグにtrueをセット
         */
        if (dealer.getCalcResult() > 21) {
            dealer.setHit(false);
        } else if (dealer.getCalcResult() == 21) {
            if (dealer.getCardList().size() == 2) {
                dealer.setBlackJack(true);
            }
            dealer.setHit(false);
        } else if (dealer.getCalcResult() >= 17) {
            dealer.setHit(false);
        } else {
            dealer.setHit(true);
        }
        
        /*
         * プレーヤーのヒットフラグがtrueならplayerGame()
         * ディーラーのヒットフラグがtrueならdealerGame()
         * ヒットフラグが両方falseになるまで再帰呼び出し
         */
        if (player.isHit()) {
            playerGame(false);
        } else if (dealer.isHit()) {
            dealerGame();
        } 
    }
    
    /** 
     * コンソール入力されたヒット(H)、またはスタンド(S)から
     * ヒットフラグを返す
     * 
     * @return isHit ヒットするときtrue
     */
    public static boolean hitOrStand() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        boolean isHit = false;

        do {
            System.out.println("[H]または[S]を入力してください");
            try {
                str = br.readLine();
                if ("h".equals(str) || "H".equals(str)) {
                    isHit = true;
                } else if ("s".equals(str) || "S".equals(str)) {
                    isHit = false;
                } else {
                    str = "";
                }
            } catch(IOException e) {
                System.out.println("入力エラー:" + e.getMessage());
                str = "";
            }
        } while ("".equals(str));

        return isHit;
    }
    
    /**
     * 勝敗を判定するメソッドです。
     */
    public static void judge(){
        
        //ブラックジャックかどうか判定
        if(player.isBlackJack() && dealer.isBlackJack()){
            System.out.println("両者ともブラックジャックです。");
            System.out.println(EVEN);
            return;
        } else if (player.isBlackJack()) {
            System.out.println("ブラックジャックです。");
            System.out.println(PLAYER_WIN);
            return;
        } else if (dealer.isBlackJack()) {
            System.out.println("ブラックジャックです。");
            System.out.println(DEALER_WIN);
            return;
        }
        
        int playerResult = player.getCalcResult();
        int dealerResult = dealer.getCalcResult();
        
        resultStr = "プレイヤー : " + playerResult + " / ディーラー : " + dealerResult;
        
        //結果の値を比較して判定
        if (playerResult > 21 && dealerResult > 21) {
            System.out.println("両者バーストで" + EVEN);
        } else if (playerResult > 21) {
            System.out.println(DEALER_WIN);
        } else if (dealerResult > 21) {
            System.out.println(PLAYER_WIN);
        } else if (playerResult == dealerResult) {
            System.out.println(EVEN);
        } else if (playerResult > dealerResult) {
            System.out.println(PLAYER_WIN);
        } else {
            System.out.println(DEALER_WIN);
        }
        System.out.println(resultStr);
    }

}
