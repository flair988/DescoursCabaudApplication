<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="productFinished">
        <h2 class="jh-entity-heading" data-cy="productFinishedDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.productFinished.detail.title')"></span> {{ productFinished.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.productFinished.itemName')"></span>
          </dt>
          <dd>
            <span>{{ productFinished.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.productFinished.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ productFinished.kingdeeId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.productFinished.supplier')"></span>
          </dt>
          <dd>
            <span>{{ productFinished.supplier }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.productFinished.supplierEmail')"></span>
          </dt>
          <dd>
            <span>{{ productFinished.supplierEmail }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.productFinished.orderDate')"></span>
          </dt>
          <dd>
            <span>{{ productFinished.orderDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.productFinished.cateGory')"></span>
          </dt>
          <dd>
            <span>{{ productFinished.cateGory }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.productFinished.remark')"></span>
          </dt>
          <dd>
            <span>{{ productFinished.remark }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.productFinished.materialReceiptDate')"></span>
          </dt>
          <dd>
            <span>{{ productFinished.materialReceiptDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.productFinished.docStatus')"></span>
          </dt>
          <dd>
            <span>{{ productFinished.docStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.productFinished.supplierName')"></span>
          </dt>
          <dd>
            <span>{{ productFinished.supplierName }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="productFinished.id"
          :to="{ name: 'ProductFinishedEdit', params: { productFinishedId: productFinished.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./product-finished-details.component.ts"></script>
