<template>
  <section class="outdoor">
    <h1>Outdoor</h1>
    <ProductList category="outdoor"/>
  </section>
</template>

<script>
import ProductList from '@/components/ProductList'
import SideMainLayout from '@/layout/SideMainLayout'

export default {
  name: 'index',
  components: {
    ProductList,
    SideMainLayout
  }
}
</script>

<style>
</style>
