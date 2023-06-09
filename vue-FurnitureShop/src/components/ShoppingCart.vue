<template>
  <div class="login" v-if="$store.state.username.length <= 0">
    <router-link to="/login" exact>
      <button>login</button>
    </router-link>
  </div>
  <div class="shopping-cart" v-else>
    <div v-if="$store.state.cart.length <= 0" class="empty-cart">
      <p>Your cart is currently empty.</p>
      <router-link to="/">
        <button>Shop Now!</button>
      </router-link>
    </div>
    <div v-else class="shopping-cart-items">
      <ul>
        <li v-for="product in cartProducts" class="cart-product-card" tabindex="0">
          <div v-if="product.quantity>0 && user!='none'" class="cart-product-card">
            <img :src="`./static/images/${product.img}`" :alt="`Image of ${product.name}`">
            <span class="product-title">{{product.name}}</span>
            <span class="product-price"> {{product.price | currency}}</span>
            <span class="product-cart-quantity">Quantity: {{product.quantity}}</span>
            <div>
              <button @click="reduceProductFromCart(product)" class="cart-item-btn">-</button>
              <button @click="addProductToCart(product)" class="cart-item-btn">+</button>
            </div>
          </div>
        </li>
      </ul>
      <div class="cart-checkout">
        <h3>Cart total : </h3>
        <p>{{ total | currency}}</p>
        <!-- <button :disabled="$store.state.cart.length <= 0" @click="$store.dispatch('checkout')">Checkout</button>
        <p class="status" v-if="$store.state.checkoutStatus">{{$store.state.checkoutStatus}}</p> -->
        <!-- <button :disabled="$store.state.cart.length <= 0" @click="$store.dispatch('checkout')">Checkout</button> -->
        <button @click="checkout(user)">Checkout</button>
        <p class="status" v-if="checkoutStatus">{{checkoutStatus}}</p>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState, mapGetters, mapActions} from 'vuex'

export default {
  name: 'ShoppingCart',
  data(){
    return {
      loading: false,
    };
  },
  computed: {
    ...mapGetters({
      cartProducts:'cartProducts',
      total:'cartTotal'
    }),
    ...mapState({
      checkoutStatus: 'checkoutStatus'
    }),
    noItems() {
      return this.$store.state.cartItems
    },
    cartList() {
      return this.$store.state.cart
    },
    user() {
      return this.$store.state.username
    }
  },
  methods: {
    ...mapActions({
      checkout: 'checkout',
      reduceProductFromCart: 'reduceProductFromCart',
      addProductToCart: 'addProductToCart',
      fetchCart: 'fetchCart'
    })
  },
  created(){
    this.loading = true
    this.fetchCart(this.$store.state.username)
      .then(() => this.loading = false)
  }
}
</script>

<style lang="css" scoped>
.cart-product-card img {
    object-fit: contain; 
  }
.empty-cart {
  position: absolute;
  top: 45%;
  left: 50%;
  transform: translate(-50%,-45%);
}

.empty-cart p {
  margin-bottom: 30px;
}

.shopping-cart-items {
  display: flex;
  flex-direction: row;
}

ul {
  display: flex;
  flex-wrap: wrap;
  max-width: 800px;
  flex: 2;
  padding-left: 0;
  justify-content: center;
}

.cart-checkout {
  flex: 1;
  justify-content: center;
}

.cart-checkout p {
  margin-top: 10px;
  margin-bottom: 20px;
  font-size: 18px;
}

.cart-product-card {
  display: flex;
  flex-direction: column;
  margin-right: 40px;
  margin-bottom: 40px;
}

.cart-product-card:hover, .cart-product-card:focus  {
  box-shadow: 1px 0rem 14px 0px #eee;
   outline: none;
 }
.product-price {
  margin-bottom: 5px;
}

img {
  width: 180px;
  height: 240px;
}

@media(max-width: 600px) {
  .shopping-cart-items {
    flex-direction: column;
  }

  .cart-product-card {
    margin-right: 0px;
  }
  ul {
    justify-content: space-around;
  }

  /*img {
    width: auto;
  }*/
  .cart-item-btn {
   opacity: 0;
   font-size:40px
 }

 @media (max-width: 600px) {
   .cart-item-btn {
     opacity: 1;
   }
 }
 .cart-product-card:hover .cart-item-btn{
   opacity: 1;
 }
 .cart-product-card:focus .cart-item-btn {
   opacity: 1;
 }
 .cart-item-btn:focus {
   opacity: 1;
 }
 
}
</style>
