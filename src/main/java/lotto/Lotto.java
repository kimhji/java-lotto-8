package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import camp.nextstep.edu.missionutils.Randoms;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public Lotto() {
        this.numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        Set<Integer> numberNoDup = new HashSet<Integer>();
        for(Integer one: numbers){
            numberNoDup.add(one);
            Lotto.isValidLottoNumber(one);
        }
        if(numberNoDup.size() != 6){
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복되어선 안됩니다.");
        }
    }

    public static void isValidLottoNumber(int number){
        if(number<1 || number>45){
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이여야 합니다.");
        }
    }

    public void print(){
        System.out.print("[");
        for(int i = 0;i<this.numbers.size();i++){
            System.out.print(String.valueOf(this.numbers.get(i)));
            if(i < this.numbers.size()-1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
