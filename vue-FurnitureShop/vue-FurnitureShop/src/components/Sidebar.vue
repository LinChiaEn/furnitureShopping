<template>
  <aside>
    <div>
      <input type="text" v-model="searchQuery" @input="search" placeholder="搜索">
      <p></p>
    </div>
    <div class="aside-block">
      <label for="pricerange">Maximum Price: <span>${{ pricerange }}</span></label>
      <input
        class="slider"
        id="pricerange"
        tabindex="0"
        :value="pricerange"
        type="range"
        :min="min"
        :max="max"
        step="0.1"
        @input="updateHighPrice($event)"
      />
      <span class="min">${{ min }}</span>
      <span class="max">${{ max }}</span>
    </div>
    <div class="aside-block">
        <h4>Super Sale</h4>
        <label class="checkbox-control">
          <span class="label-name">Show only sale item</span>
          <input type="checkbox" v-model="check" @change="updateSale">
          <div class="checkbox-box"></div>
        </label>
    </div>
    <!-- <div class="aside-block">
      <h4>Support</h4>
      <p>Get in touch with us for any queries at <a href="#">support@bazaaar.in</a></p>
    </div> -->
  </aside>
</template>

<script>
import store from '@/store/index'
export default {
  name: 'Sidebar',
  data() {
    return {
      min: 0,
      max: this.$store.state.highprice,
      check: this.checked,
      searchResults: [],
      searchQuery:''
    };
  },
  computed: {
    pricerange() {
      return this.$store.state.highprice
    },
    checked() {
      return this.$store.state.sale;
    }
  },
  methods: {
    updateHighPrice($event) {
      this.$store.commit('setHighPrice', $event.target.value)
    },
    updateSale() {
      this.$store.commit('toggleSale');
    },
    search() {
      // 执行搜索逻辑，例如从数据源中过滤匹配的结果
      this.searchResults = this.$store.state.products.filter(item => {
        // 这里可以根据实际需求自定义搜索逻辑
        return item.name.toLowerCase().includes(this.searchQuery.toLowerCase());
      });
      this.$store.commit('setProducts', this.searchResults)
    }
  }

}
</script>

<style lang="css">
  .aside-block {
    padding: 40px 0;
    border-bottom: 1px solid #eee;
    font-size: 15px;
  }
  .min {
    float: left;
  }
  .max {
    float: right;
  }

  .aside-block h4 {
    margin-bottom: 10px;
  }
  .checkbox-control {
    position: relative;
    display: inline-block;
  }
  .checkbox-box {
    width: 18px;
    height: 18px;
    border: 2px solid #131313;
    position: relative;
    cursor: pointer;
    float: left;
    top: 2px;
    margin-right: 10px;
  }

  input[type="checkbox"] {
    opacity: 0;
    position: absolute;
  }

  input[type="checkbox"]:hover ~ .checkbox-box, input[type="checkbox"]:focus ~ .checkbox-box, input[type="checkbox"]:checked ~ .checkbox-box{
    border-color: #5044ff;
  }

  input[type="checkbox"]:checked ~ .checkbox-box::before {
    content: '';
    position: absolute;
    width: 10px;
    height: 10px;
    top: 2px;
    left: 2px;
    background: #5044ff;
  }

</style>
