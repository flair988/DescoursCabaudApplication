<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="supplier">
        <h2 class="jh-entity-heading" data-cy="supplierDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.supplier.detail.title')"></span> {{ supplier.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.client')"></span>
          </dt>
          <dd>
            <span>{{ supplier.client }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.category')"></span>
          </dt>
          <dd>
            <span>{{ supplier.category }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.subCategory')"></span>
          </dt>
          <dd>
            <span>{{ supplier.subCategory }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.capStatus')"></span>
          </dt>
          <dd>
            <span>{{ supplier.capStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.supplierStatus')"></span>
          </dt>
          <dd>
            <span>{{ supplier.supplierStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.qualificationScore')"></span>
          </dt>
          <dd>
            <span>{{ supplier.qualificationScore }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.bopeScore')"></span>
          </dt>
          <dd>
            <span>{{ supplier.bopeScore }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.internalSupplierId')"></span>
          </dt>
          <dd>
            <span>{{ supplier.internalSupplierId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.contact')"></span>
          </dt>
          <dd>
            <span>{{ supplier.contact }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.contactPhoneNumber')"></span>
          </dt>
          <dd>
            <span>{{ supplier.contactPhoneNumber }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.contactEmailAddress')"></span>
          </dt>
          <dd>
            <span>{{ supplier.contactEmailAddress }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.operationSite')"></span>
          </dt>
          <dd>
            <span>{{ supplier.operationSite }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.address')"></span>
          </dt>
          <dd>
            <span>{{ supplier.address }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.website')"></span>
          </dt>
          <dd>
            <span>{{ supplier.website }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.relationStartingYear')"></span>
          </dt>
          <dd>
            <span>{{ supplier.relationStartingYear }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.businessLicense')"></span>
          </dt>
          <dd>
            <span>{{ supplier.businessLicense }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.rexOriginStatus')"></span>
          </dt>
          <dd>
            <span>{{ supplier.rexOriginStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.createDate')"></span>
          </dt>
          <dd>
            <span>{{ supplier.createDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.updateDate')"></span>
          </dt>
          <dd>
            <span>{{ supplier.updateDate }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.item')"></span>
          </dt>
          <dd>
            <span>{{ supplier.item }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.subItems')"></span>
          </dt>
          <dd>
            <span>{{ supplier.subItems }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.date')"></span>
          </dt>
          <dd>
            <span>{{ supplier.date }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ supplier.kingdeeId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.region')"></span>
          </dt>
          <dd>
            <span>{{ supplier.region }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.supplierType')"></span>
          </dt>
          <dd>
            <span>{{ supplier.supplierType }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.remark')"></span>
          </dt>
          <dd>
            <span>{{ supplier.remark }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.supplier.frenchName')"></span>
          </dt>
          <dd>
            <span>{{ supplier.frenchName }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="supplier.id" :to="{ name: 'SupplierEdit', params: { supplierId: supplier.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./supplier-details.component.ts"></script>
