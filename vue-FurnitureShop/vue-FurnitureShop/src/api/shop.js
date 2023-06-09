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

  getCart (cb) {
    let _cart=[];
    axios.get(PRODUCT_API_BASE_URL+'/cart').then(function (response){
      _cart = response.data;
      console.log(response);
    })
    setTimeout(() => cb(_cart), 100)
  },

  getSearch(cb,keyword) {
    let _products=[];
    axios.get(PRODUCT_API_BASE_URL+'/view/products?keyword='+keyword).then(function (response){
      _products = response.data;
      console.log(response);
    })
    setTimeout(() => cb(_products), 100)
  },

  buyProducts (products, cb, errorCb) {
    setTimeout(() => {
      // simulate random checkout failure.
      (Math.random() > 0.5 || navigator.userAgent.indexOf('PhantomJS') > -1)
        ? cb()
        : errorCb()
    }, 100)
  },

  addProductToCart(product){
    axios.post(PRODUCT_API_BASE_URL+'/cart/add',{
      name: product.name,
      img: product.img,
      category: product.category,
      sale: product.sale,
      price: product.price
    })
  },
  popProductFromCart(product){
    axios.delete(PRODUCT_API_BASE_URL+'/cart/reduce/'+
    product.id)
  },

  getAccount(user){
    axios.post(PRODUCT_API_BASE_URL+'/login',{
      username: user[0],
      password: user[1]
    })
    
  }
}
