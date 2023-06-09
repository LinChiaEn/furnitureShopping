<template>
  <section class="sofa">
    <h1>Sofa</h1>
    <ProductList category="sofa"/>
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
