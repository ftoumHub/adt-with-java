package org.demo.functor;

import org.demo.applicative.MyApplicative;
import org.demo.domain.Item;
import org.demo.domain.Item.Price;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class MyFunctorTest {

    @Test
    public void reduce_and_display_price_imperative_style() {
        Item item = new Item("book", new Price("$", 50));

        item.getPrice().setAmount(item.getPrice().getAmount() - 5);
        String result = "price: " + item.getPrice().getAmount();

        assertThat(item.getPrice().getAmount()).isEqualTo(45); //mutated Price instance
        assertThat(result).isEqualTo("price: 45");
    }

    @Test
    public void reduce_and_display_price_functional_style() {
        Item item = new Item("book", new Price("$", 50));

        MyFunctor<Item> functor = MyFunctor.of(item);

        String result = functor
                .map(Item::getPrice)
                .map(Price::getAmount)
                .map(amount -> amount - 5)
                .map(p -> "price: " + p)
                .get();

        assertThat(item.getPrice().getAmount()).isEqualTo(50); // we prevent Price mutation
        assertThat(result).isEqualTo("price: 45");
    }

    @Test
    public void functors_cannot_fmap_on_functors() {
        Item item = new Item("book", new Item.Price("$", 50));

        MyFunctor<Item> functor = MyFunctor.of(item);
        // we want our discount function to be wrapped into a functor
        MyFunctor<Function<Integer, Integer>> discount = MyFunctor.of(amount -> amount - 5);

    /*   String result = functor
                .fmap(Item::getPrice)
                .fmap(Item.Price::getAmount)
                .fmap(discount) // does not compile
                .fmap(p -> "price: " + p)
                .get();*/

        // Could not map MyFunctor(50) with MyFunctor(amount -> amount - 5), only accepts simple Functions, but not boxed Functions
    }

    @Test
    public void applicative_can_apply_boxed_functions() {
        Item item = new Item("book", new Item.Price("$", 50));

        MyApplicative<Item> applicative = MyApplicative.of(item);
        MyApplicative<Function<? super Integer, ? extends Integer>> discountApplicative = MyApplicative.of(amount -> amount - 5);

        // can also map because Applicative is a functor - map is a subset of apply.
        String result = applicative
                .map(Item::getPrice)
                .map(Item.Price::getAmount)
                .apply(discountApplicative)
                .map(p -> "price: " + p)
                .get();

        assertThat(result).isEqualTo("price: 45");
    }

    @Test
    public void html_functor_should_map_value() {
        MyFunctor<Integer> price = MyFunctor.of(42);

        BigDecimal vat = BigDecimal.TEN;

        MyFunctor<BigDecimal> fmap = price
                .map(BigDecimal::new)
                .map(x -> x.add(vat));

        assertThat(fmap.get()).isEqualTo(new BigDecimal("52"));
    }

    /**@Test
    public void functor_should_go_deeper_inside_objects() {
        DaFunctor<Person> person = DaFunctor.of(new Person("jean", new Person.Address("78120", "Rbt"), 18, NotExistBoxed.INSTANCE));

        String city = person.map(Person::getAddress).map(Person.Address::getCity).get();
        int birthday = person.DaFunctormap(p -> p.getAge() + 1).get();

        assertThat(birthday).isEqualTo(19);
        assertThat(city).isEqualTo("Rbt");
    }*/

}
