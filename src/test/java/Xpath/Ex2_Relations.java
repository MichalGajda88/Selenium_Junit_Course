package Xpath;

public class Ex2_Relations {
    //1. Zaznacz cenę jednego z produktów. Najpierw się umów sam lub sama ze sobą, którym produktem chcesz się zająć.
    // Chodzi dokładnie o element <span> przechowujący cenę produktu. Na filmie tłumaczę dokładnie o co mi chodzi.

    // .//a[@data-product_id='393']/../following-sibling::td[@class='product-price']/span

    //2. Zaznacz kwotę w podsumowaniu koszyka.

    // .//tr[@class='cart-subtotal']/td/span
}
