import shop from '@/api/shop'

export default { // setting and updating the state
  setProducts(state,products) {
    state.products = products
  },

  setCart(state,cart) {
    state.cart = cart
  },

  setCartItems(state,cartItems) {
    state.cartItems = cartItems
  },

  pushProductToCart(state,productName) {
    state.cart.push({
      name: productName,
      quantity: 1
    })
    state.cartItems++
  },
  popProductFromCart(state,productId) {
    //state.cart.pop()
  },
  decrementItemQty(state,cartItem){
    state.cartItems--,
    cartItem.quantity--
    //console.log(state.cart.length)
  },
  incrementItemQty(state,cartItem) {
    cartItem.quantity++
    state.cartItems++
    console.log(state.cart.length)
  },
  emptyItemQty(state,cartItem){
    cartItem.quantity--
    state.cartItems--
   
    for(let i=0;i<state.cart.length;i++){
      if(state.cart[i].name == cartItem.name){
        state.cart.splice(i,1)
        break
      }
    }
  },
  decrementProductInventory(state,product) {
    product.inventory--
  },
  setCheckoutStatus(state,status) {
    state.checkoutStatus = status
  },
  emptyCart(state) {
    state.cart = []
    state.cartItems = 0
  },
  setHighPrice(state, event) {
    state.highprice = event
  },
  toggleSale(state) {
    state.sale = !state.sale
  }
}
