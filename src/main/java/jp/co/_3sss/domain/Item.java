package jp.co._3sss.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="items")
public class Item {
  
  @Id
  @GeneratedValue
  private Long id;
  
  @NotNull(message="商品名を入力してください")
  @Length(min=1,max=100, message="商品名は1文字以上100文字以下で入力してください")
  private String name;
  
  @NotNull(message="価格を入力してください")
  @Min(value=0, message="価格は0円以上を入力してください")
  private int price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
  
}
