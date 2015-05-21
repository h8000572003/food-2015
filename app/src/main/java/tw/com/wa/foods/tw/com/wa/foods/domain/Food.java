package tw.com.wa.foods.tw.com.wa.foods.domain;

/**
 * Created by Andy on 15/5/18.
 */
public class Food {
    private Long id;

    /**
     *
     */
    private int dollar = 0;
    private String name = "";
    private int no = 0;


    public Food(String name, int dollar) {
        this.dollar = dollar;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDollar() {
        return dollar;
    }

    public void setDollar(int dollar) {
        this.dollar = dollar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
