import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Product{
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o){
        // 성능 최적화와 객체 동일성의 빠른 판단을 위해 참조 비교
        // 만일 참조 비교를 하지 않고 객체의 내용을 비교한다면 객체가 달라도 내용이 동일한지 비교해야 하므로 불필요한 연산 발생해 성능에 영향을 줄 수 있다.

        /* 객체의 참조 비교 */
        if(this == o) return true;
        // object 클래스의 getClass() 메소드 : 현재 참조하고 있는 클래스를 확인할 수 있는 메소드
        // 즉, 현재객체와 비교대상이 동일한 객체를 참조하지 않다면, 비교대상이 비여있거나 현재 객체의 클래스와 객체 o의 클래스가 같지 않은지 비교
        if(o == null || getClass() != o.getClass()) return false;

        /* 객체의 내용 비교 */
        Product product = (Product) o;
        if(Double.compare(product.price, price) != 0) return false;
        return name.equals(product.name);
    }

    @Override
    public int hashCode(){
        int result;
        long temp;
        // 필드 name의 해시 코드를 추가
        result = name.hashCode();
        // double 필드인 price를 long 값으로 변환
        temp = Double.doubleToLongBits(price);
        // 필드 price의 해시 코드를 추가
        // (일반적으로 사용하는 연산 방법으로 해시 코드의 충돌을 최소화하고 연산의 효율성을 높여준다.)
        result = 31 * result + (int)(temp ^ (temp >>> 32));
        return result;
    }

    public static Product importCSV(String line){
        String[] values = line.split(", ");
        String name = values[0].trim();
        double price = Double.parseDouble(values[1].trim());

        return new Product(name, price);
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

public class shoppingBasket {
    public static void main(String[] args) {
        // 상품 목록 생성
        Set<Product> productSet = new HashSet<>();
        // TODO: 상품 클래스를 생성하여 상품목록에 넣는다.
        productSet = loadProductListFromCSV("ShoppingBasket/products.csv");

        // 장바구니 생성
        Map<Product, Integer> cart = new HashMap<>();

        // TODO: 상품을 장바구나에 추가
        addProduct(cart, productSet, "냉장고", 2);
        addProduct(cart, productSet, "TV", 1);
        addProduct(cart, productSet, "세탁기", 1);
        addProduct(cart, productSet, "세탁기", 1); // 중복확인
        addProduct(cart, productSet, "가습기", 1); // 상품목록에 없는 품목

        showItems(cart);

        // TODO: 상품을 장바구니에서 제거
        removeProduct(cart, productSet, "냉장고", 1);
        removeProduct(cart, productSet, "냉장고", 1); // 상품 완전 제거
        removeProduct(cart, productSet, "세탁기", 3); // 장바구니에 담은 개수 초과
        removeProduct(cart, productSet, "에어컨", 1); // 장바구니에 없는 품목

        // TODO: 장바구니에 현재 담긴 상품들을 출력 (상품이름, 각 상품의 갯수)
        showItems(cart);
    }

    private static Product findProduct(Set<Product> productSet, String productName){
        Product newProduct = null;

        for(Product product : productSet){
            if(product.getName().equals(productName)) {
                newProduct = product;
                return newProduct;
            }
        }

        return null;
    }

    private static void addProduct(Map<Product, Integer> cart, Set<Product> productSet, String productName, int quantitiy){
        Product product = findProduct(productSet, productName);

        if(product != null){
            cart.put(product, cart.getOrDefault(product, 0) + quantitiy);
            System.out.println(quantitiy + "개의 " + productName + "이(가) 장바구니에 추가되었습니다.");
        }
        else {
            System.out.println(productName + "은(는) 상품 목록에 없습니다.");
        }
    }

    private static void removeProduct(Map<Product, Integer> cart,  Set<Product> productSet, String productName, int quantitiy){
        Product product = findProduct(productSet, productName);

        if(cart.containsKey(product)){
            int currentQuantitiy =  cart.get(product);

            if(currentQuantitiy > quantitiy){
                cart.put(product, currentQuantitiy - quantitiy);
                System.out.println(quantitiy + "개의 " + product.getName() + "이(가) 장바구니에서 제거되었습니다.");
            }
            else if(currentQuantitiy == quantitiy){
                cart.remove(product);
                System.out.println(product.getName() + "이(가) 장바구니에서 모두 제거되었습니다.");
            }
            else{
                System.out.println("장바구니에 담은 수량보다 많은 수량을 제거할 수 없습니다.");
            }
        }
        else{
            System.out.println("해당하는 상품은 장바구니에 없습니다.");
        }
    }

    private static void showItems(Map<Product, Integer> cart){
        double totalPrice = 0;

        System.out.println();
        System.out.println("----<< 장바구니 >>-----");
        for(Map.Entry<Product, Integer> entry : cart.entrySet()){
            totalPrice += entry.getKey().getPrice();
            System.out.println(entry.getKey().getName() + " : " + entry.getValue() + "개");
        }
        System.out.println("---------------------");
        System.out.println("총액 : " + totalPrice + "원");
        System.out.println();
    }

    private static Set<Product> loadProductListFromCSV(String filePath){
        Set<Product> productSet = new HashSet<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                Product product = Product.importCSV(line);
                productSet.add(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return productSet;
    }
}
