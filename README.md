## GraphQl Basic
After the application starting 

* [Open GraphQl UI](http://localhost:8080/graphiql)
* Send query (get list of product)
  ```graphql 
  query {
    productList{
       id, label, price, category{
          id, label
       }
    }
  }
  ```
* Send query (get one product). We don't have recursive issue like Rest
  ```graphql
  query {
    productById(id: "c7720eee-a388-4f55-82e1-57b76ccea764"){
       id, label,price, category {
         id, label, products {
           id, label
         }
       }
    }
  }
  ```

* Send mutation without variables
  ```graphql
  mutation {
    saveProduct(productRequest :
       { label : "Macbook pro",
        price : 67000,
        quantity: 3,
        categoryId : 2
       })
      {
        id, label, price, quantity, category {
        id,label
      }
    }
  }
  ```

* Send mutation with variables
    ```graphql
  mutation($label:String, $price: Float,$quantity: Int, $categoryId:Float) {
    saveProduct(productRequest :
       { label : $label,
        price : $price,
        quantity: $quantity,
        categoryId : $categoryId
       })
      {
        id, label, price, quantity, category {
        id,label
      }
    }
  }
  ```
