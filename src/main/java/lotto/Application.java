package lotto;

import java.util.ArrayList;
import java.util.List;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        Lotto[] boughtLottos = buyLottoPlusInput();
        long money = boughtLottos.length*1000;
        Lotto rightLotto = getRightLottoPlusInput();
        int bonusNumber = getBonusNumberPlusInput(rightLotto);

        int[] ranks = new int[5];
        getRanks(ranks, boughtLottos, rightLotto, bonusNumber);
        printRanks(ranks);

        long earnMoney = getEarnMoney(ranks);
        double profit = getProfit(money, earnMoney);

        printProfit(profit);
    }

    private static Lotto[] buyLottoPlusInput(){
        Lotto[] lottos = null;
        while (true) { 
            try{
                System.out.println("구입금액을 입력해 주세요.");
                int money = Integer.parseInt(Console.readLine());
                System.out.println();
                lottos = buyLotto(money);
                break;
            }
            catch(NumberFormatException e){
                System.out.println("[ERROR] 숫자만 입력해주세요.");
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
        return lottos;
    }

    private static Lotto[] buyLotto(int money){
        Lotto[] lottos = null;
        if(money%1000 != 0) throw new IllegalArgumentException("[ERROR] 로또의 가격은 1000원입니다. 잔돈은 제외하고 천 원 단위로 입력해주세요.");
        int count = money/1000;
        if(count == 0) throw new IllegalArgumentException("[ERROR] 로또의 가격은 1000원입니다. 돈이 부족합니다.");

        System.out.println(count+"개를 구매했습니다.");
        lottos = new Lotto[count];
        for(int i = 0;i<count;i++){
            lottos[i] = new Lotto();
            lottos[i].print();
        }
        System.out.println();
        return lottos;
    }

    private static void getRanks(int[] ranks, Lotto[] lottos, Lotto rightLotto, int bonusNumber){
        for(int i = 0;i<ranks.length;i++){
            ranks[i] = 0;
        }
        
        for(Lotto lotto: lottos){
            int rank = lotto.getLottoRank(rightLotto, bonusNumber);
            if(rank <= 0) continue;
            ranks[rank-1]++;
        }
    }

    private static void printRanks(int[] ranks){
        System.out.println("당첨 통계\r\n---");
        for(int i = ranks.length-1;i>=0;i--){
            System.out.println(getRankString(i+1)+ranks[i]+"개");
        }
        System.out.println();
    }

    private static String getRankString(int rank){
        if(rank == 1) return "6개 일치 (2,000,000,000원) - ";
        if(rank == 2) return "5개 일치, 보너스 볼 일치 (30,000,000원) - ";
        if(rank == 3) return "5개 일치 (1,500,000원) - ";
        if(rank == 4) return "4개 일치 (50,000원) - ";
        if(rank == 5) return "3개 일치 (5,000원) - ";
        throw new IllegalArgumentException("[ERROR] 잘못된 rank 값이 들어왔습니다.");
    }

    private static double getProfit(long spent, long earn){
        long tnsProp = earn*100 / spent;
        if(tnsProp % 10 >= 5) tnsProp += 10;
        tnsProp /= 10;
        return ((double)tnsProp)/10;
    }

    private static long getEarnMoney(int[] ranks){
        long result = 0;
        result += 2000000000*ranks[0];
        result += 30000000*ranks[1];
        result += 1500000*ranks[2];
        result += 50000*ranks[3];
        result += 5000*ranks[4];
        return result;
    }

    private static void printProfit(double profit){
        System.out.println("총 수익률은 "+profit+"%입니다.");
        System.out.println();
    }

    private static Lotto getRightLottoPlusInput(){
        Lotto rightLotto = null;
        while (true) { 
            try{
                System.out.println("당첨 번호를 입력해 주세요.");
                List<Integer> numberList = new ArrayList<Integer>();
                String line = Console.readLine();
                if(line == null || line.isBlank()){
                    throw new IllegalArgumentException("[ERROR] 콤마로 구분된 당첨 번호 6개를 입력해주세요.");
                }
                String[] data = line.trim().split(",");
                for(String one : data){
                    numberList.add(Integer.parseInt(one));
                }
                rightLotto = new Lotto(numberList);
                System.out.println();
                break;
            }
            catch(NumberFormatException e){
                System.out.println("[ERROR] 숫자만 입력해주세요.");
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
        return rightLotto;
    }

    private static int getBonusNumberPlusInput(Lotto rightLotto){
        int bonusNumber = 0;
        while (true) { 
            try{
                System.out.println("보너스 번호를 입력해 주세요.");
                String line = Console.readLine();
                if(line == null || line.isBlank()){
                    throw new IllegalArgumentException("[ERROR] 콤마로 구분된 당첨 번호 6개를 입력해주세요.");
                }
                bonusNumber = Integer.parseInt(line.trim());
                Lotto.isValidLottoNumber(bonusNumber);
                if(rightLotto.isIncludeNumber(bonusNumber)){
                    throw new IllegalArgumentException("[ERROR] 이미 입력하신 당첨 번호와 중복될 수 없습니다.");
                }
                System.out.println();
                break;
            }
            catch(NumberFormatException e){
                System.out.println("[ERROR] 숫자만 입력해주세요.");
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
        return bonusNumber;
    }
}
