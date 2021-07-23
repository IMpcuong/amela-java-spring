package TutorialSpringMVC.Service;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ConvertMoneyService {

    public long money;
    public long rate;

    public long convertMoney() {
        return this.money * this.rate;
    }
}
