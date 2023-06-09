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

  fetchCart(context) {
    return new Promise(function(resolve, reject) {
      shop.getCart(cart => {
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
    shop.addProductToCart(product)
    console.log("hello this is addproduct")
    if(!cartItem) {
      context.commit('pushProductToCart',product.name)
    }
    else {
      context.commit('incrementItemQty',cartItem)
    }
    
    // if(product.inventory > 0)
    /*const cartItem  = context.state.cart.find(item => item.id === product.id)
    if(!cartItem) {
      context.commit('pushProductToCart',product.id)
    }
    else {
      context.commit('incrementItemQty',cartItem)
    }

    context.commit('decrementProductInventory',product)*/

  },

  reduceProductFromCart(context, product) {
    const cartItem  = context.state.cart.find(item => item.name === product.name)
    shop.popProductFromCart(product)
    if(cartItem.quantity>1){context.commit('decrementItemQty',cartItem)}
    else{context.commit('emptyItemQty',cartItem)}
  },

  checkout(context) {
    shop.buyProducts(
      context.state.cart,
      () => {
        context.commit('emptyCart')
        context.commit('setCheckoutStatus','success')
      },
      () => {
        context.commit('setCheckoutStatus','fail')
      }
    )
  },
  loginCheck(context,user){
    shop.getAccount(user)
  }
}
