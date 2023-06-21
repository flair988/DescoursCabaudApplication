<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="item">
        <h2 class="jh-entity-heading" data-cy="itemDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.item.detail.title')"></span> {{ item.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.itemStatus')"></span>
          </dt>
          <dd>
            <span>{{ item.itemStatus }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.itemFranceName')"></span>
          </dt>
          <dd>
            <span>{{ item.itemFranceName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ item.kingdeeId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.itemName')"></span>
          </dt>
          <dd>
            <span>{{ item.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.codeag')"></span>
          </dt>
          <dd>
            <span>{{ item.codeag }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.technicalDocuments')"></span>
          </dt>
          <dd>
            <span>{{ item.technicalDocuments }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.certification')"></span>
          </dt>
          <dd>
            <span>{{ item.certification }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.opportunitySheet')"></span>
          </dt>
          <dd>
            <span>{{ item.opportunitySheet }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.packingType')"></span>
          </dt>
          <dd>
            <span>{{ item.packingType }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.salePackageImage')"></span>
          </dt>
          <dd>
            <span>{{ item.salePackageImage }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.cartonLengthMilimeter')"></span>
          </dt>
          <dd>
            <span>{{ item.cartonLengthMilimeter }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.cartonHeightMilimeter')"></span>
          </dt>
          <dd>
            <span>{{ item.cartonHeightMilimeter }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.barcode')"></span>
          </dt>
          <dd>
            <span>{{ item.barcode }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.cartonWeightKg')"></span>
          </dt>
          <dd>
            <span>{{ item.cartonWeightKg }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.cartonWeightGr')"></span>
          </dt>
          <dd>
            <span>{{ item.cartonWeightGr }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.cartonWidthMilimeter')"></span>
          </dt>
          <dd>
            <span>{{ item.cartonWidthMilimeter }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.productDescriptionAndFonctionalities')"></span>
          </dt>
          <dd>
            <span>{{ item.productDescriptionAndFonctionalities }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.drawing')"></span>
          </dt>
          <dd>
            <span>{{ item.drawing }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.userManual')"></span>
          </dt>
          <dd>
            <span>{{ item.userManual }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.palletSize')"></span>
          </dt>
          <dd>
            <span>{{ item.palletSize }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.typeOfMarketing')"></span>
          </dt>
          <dd>
            <span>{{ item.typeOfMarketing }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.productPic')"></span>
          </dt>
          <dd>
            <span>{{ item.productPic }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.label')"></span>
          </dt>
          <dd>
            <span>{{ item.label }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.comment')"></span>
          </dt>
          <dd>
            <span>{{ item.comment }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.productTaxonomy')"></span>
          </dt>
          <dd>
            <span>{{ item.productTaxonomy }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.netWeight')"></span>
          </dt>
          <dd>
            <span>{{ item.netWeight }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.grossWeight')"></span>
          </dt>
          <dd>
            <span>{{ item.grossWeight }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.unitOfWeight')"></span>
          </dt>
          <dd>
            <span>{{ item.unitOfWeight }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.item.cartonVolumeMilimeter')"></span>
          </dt>
          <dd>
            <span>{{ item.cartonVolumeMilimeter }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="item.id" :to="{ name: 'ItemEdit', params: { itemId: item.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./item-details.component.ts"></script>
