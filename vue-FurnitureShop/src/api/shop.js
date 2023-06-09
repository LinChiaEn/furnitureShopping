import axios from "axios";

const PRODUCT_API_BASE_URL = 'http://localhost:8080/shop'


export default {
  getProducts (cb) {
    let _products=[];
    axios.get(PRODUCT_API_BASE_URL+'/view/products').then(function (response){
      _products = response.data;
      console.log(response);
    })
    setTimeout(() => cb(_products), 100)
  },

  getCart (username,cb) {
    let _cart=[];
    axios.get(PRODUCT_API_BASE_URL+'/cart/'+username).then(function (response){
      _cart = response.data;
      console.log(username);
    })
    setTimeout(() => cb(_cart), 100)
  },

  buyProducts (cart, cb) {
    axios.post(PRODUCT_API_BASE_URL+'/cart/order',{
      receiver: "justin",
      address: "NCKUCSIE",
      order: cart
    })
    setTimeout(() => cb(), 100)
  },

  removeAllTtemsFromCart(username){
    axios.delete(PRODUCT_API_BASE_URL+'/cart/'+username+'/remove')
  },

  addProductToCart(product,username){
    console.log("addProductToCart")
    axios.post(PRODUCT_API_BASE_URL+'/cart/'+username+'/add',{
      name: product.name,
      img: product.img,
      category: product.category,
      sale: product.sale,
      price: product.price
    })
  },
  popProductFromCart(product,username){
    axios.delete(PRODUCT_API_BASE_URL+'/cart/'+username+'/reduce/'+
    product.name)
  },

  getAccount(user, cb){
    let loginuser = ""
    axios.post(PRODUCT_API_BASE_URL+'/login',{
      username: user[0],
      password: user[1]
    }).then(function (response){
      let check = response.status;
      console.log(response);
      if(check == "202") loginuser = user[0]
      else loginuser = "none"
    })
    setTimeout(() => cb(loginuser), 100)
  }
}
