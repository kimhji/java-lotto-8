package lotto;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
    }

    public void buyLotto(Lotto[] lottos, int money){
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
}
