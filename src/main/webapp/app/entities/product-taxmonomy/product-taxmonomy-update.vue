<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.productTaxmonomy.home.createOrEditLabel"
          data-cy="ProductTaxmonomyCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.productTaxmonomy.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="productTaxmonomy.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="productTaxmonomy.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productTaxmonomy.itemId')"
              for="product-taxmonomy-itemId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemId"
              id="product-taxmonomy-itemId"
              data-cy="itemId"
              :class="{ valid: !v$.itemId.$invalid, invalid: v$.itemId.$invalid }"
              v-model="v$.itemId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productTaxmonomy.kingdeeId')"
              for="product-taxmonomy-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="product-taxmonomy-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productTaxmonomy.itemName')"
              for="product-taxmonomy-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="product-taxmonomy-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productTaxmonomy.groupName')"
              for="product-taxmonomy-groupName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="groupName"
              id="product-taxmonomy-groupName"
              data-cy="groupName"
              :class="{ valid: !v$.groupName.$invalid, invalid: v$.groupName.$invalid }"
              v-model="v$.groupName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productTaxmonomy.parentGroupName')"
              for="product-taxmonomy-parentGroupName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="parentGroupName"
              id="product-taxmonomy-parentGroupName"
              data-cy="parentGroupName"
              :class="{ valid: !v$.parentGroupName.$invalid, invalid: v$.parentGroupName.$invalid }"
              v-model="v$.parentGroupName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productTaxmonomy.subGroupName')"
              for="product-taxmonomy-subGroupName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="subGroupName"
              id="product-taxmonomy-subGroupName"
              data-cy="subGroupName"
              :class="{ valid: !v$.subGroupName.$invalid, invalid: v$.subGroupName.$invalid }"
              v-model="v$.subGroupName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productTaxmonomy.description')"
              for="product-taxmonomy-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="product-taxmonomy-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./product-taxmonomy-update.component.ts"></script>
