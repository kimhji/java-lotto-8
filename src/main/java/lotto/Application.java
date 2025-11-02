package lotto;

public class Application {
    public static void main(String[] args) {
        Lotto[] boughtLottos = null;
        int money = 0;
        buyLotto(boughtLottos, money);


    }

    public static void buyLotto(Lotto[] lottos, int money){
        if(money%1000 != 0) throw new IllegalArgumentException("[ERROR] 로또의 가격은 1000원입니다. 잔돈은 제외하고 천 원 단위로 입력해주세요.");
        int count = money/1000;
        if(count == 0) throw new IllegalArgumentException("[ERROR] 로또의 가격은 1000원입니다. 돈이 부족합니다.");

        System.out.println(count+"개를 구매했습니다.");
        lottos = new Lotto[count];
        for(int i = 0;i<count;i++){
            lottos[i] = new Lotto();
            lottos[i].print();
        }
    }

    public static void getRanks(int[] ranks, Lotto[] lottos, Lotto rightLotto, int bonusNumber){
        for(int i = 0;i<ranks.length;i++){
            ranks[i] = 0;
        }
        
        for(Lotto lotto: lottos){
            int rank = lotto.getLottoRank(rightLotto, bonusNumber);
            if(rank <= 0) continue;
            ranks[rank-1]++;
        }
    }

    public static void printRanks(int[] ranks){
        for(int i = 0;i<ranks.length;i++){
            System.out.println(getRankString(i+1)+ranks[i]+"개");
        }
    }

    private static String getRankString(int rank){
        if(rank == 1) return "6개 일치 (2,000,000,000원) - ";
        if(rank == 2) return "5개 일치, 보너스 볼 일치 (30,000,000원) - ";
        if(rank == 3) return "5개 일치 (1,500,000원) - ";
        if(rank == 4) return "4개 일치 (50,000원) - ";
        if(rank == 5) return "3개 일치 (5,000원) - ";
        throw new IllegalArgumentException("[ERROR] 잘못된 rank 값이 들어왔습니다.");
    }
}
