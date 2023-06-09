import shop from '@/api/shop'

export default { // actions = mehtods
  fetchProducts(context) {
    return new Promise(function(resolve, reject) {
      shop.getProducts(products => {
        context.commit('setProducts',products)
        resolve();
      })
      
    })
  },

  fetchCart(context,username) {
    console.log("ddd"+username)
    return new Promise(function(resolve, reject) {
      shop.getCart(username,
        cart => {
        context.commit('setCart',cart)
        resolve();
        var cartItems=0
        for(var i=0;i<cart.length;i++){
          cartItems+=cart[i].quantity
        }
        context.commit('setCartItems',cartItems)
      })
    })
  },

  addProductToCart(context, product) {
    const cartItem  = context.state.cart.find(item => item.name === product.name)
    console.log(context.state.username)
    shop.addProductToCart(product,context.state.username)
    if(!cartItem) {
      context.commit('pushProductToCart',product.name)
    }
    else {
      context.commit('incrementItemQty',cartItem)
    }

  },

  reduceProductFromCart(context, product) {
    const cartItem  = context.state.cart.find(item => item.name === product.name)
    shop.popProductFromCart(product,context.state.username)
    if(cartItem.quantity>1){context.commit('decrementItemQty',cartItem)}
    else{context.commit('emptyItemQty',cartItem)}
  },

  checkout(context,username) {
    shop.buyProducts(
      context.state.cart,
      () => {
        context.commit('emptyCart')
        context.commit('setCheckoutStatus','success')
      }
    )
    shop.removeAllTtemsFromCart(username)
  },
  loginCheck(context,user){
    shop.getAccount(user,
      username => {
        context.commit('setUsername',username)
      })
  }
}
