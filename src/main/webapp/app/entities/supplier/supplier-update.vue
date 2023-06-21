<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.supplier.home.createOrEditLabel"
          data-cy="SupplierCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.supplier.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="supplier.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="supplier.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.supplier.client')" for="supplier-client"></label>
            <input
              type="text"
              class="form-control"
              name="client"
              id="supplier-client"
              data-cy="client"
              :class="{ valid: !v$.client.$invalid, invalid: v$.client.$invalid }"
              v-model="v$.client.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.supplier.category')" for="supplier-category"></label>
            <input
              type="text"
              class="form-control"
              name="category"
              id="supplier-category"
              data-cy="category"
              :class="{ valid: !v$.category.$invalid, invalid: v$.category.$invalid }"
              v-model="v$.category.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.subCategory')"
              for="supplier-subCategory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="subCategory"
              id="supplier-subCategory"
              data-cy="subCategory"
              :class="{ valid: !v$.subCategory.$invalid, invalid: v$.subCategory.$invalid }"
              v-model="v$.subCategory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.capStatus')"
              for="supplier-capStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="capStatus"
              id="supplier-capStatus"
              data-cy="capStatus"
              :class="{ valid: !v$.capStatus.$invalid, invalid: v$.capStatus.$invalid }"
              v-model="v$.capStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.supplierStatus')"
              for="supplier-supplierStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplierStatus"
              id="supplier-supplierStatus"
              data-cy="supplierStatus"
              :class="{ valid: !v$.supplierStatus.$invalid, invalid: v$.supplierStatus.$invalid }"
              v-model="v$.supplierStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.qualificationScore')"
              for="supplier-qualificationScore"
            ></label>
            <input
              type="text"
              class="form-control"
              name="qualificationScore"
              id="supplier-qualificationScore"
              data-cy="qualificationScore"
              :class="{ valid: !v$.qualificationScore.$invalid, invalid: v$.qualificationScore.$invalid }"
              v-model="v$.qualificationScore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.bopeScore')"
              for="supplier-bopeScore"
            ></label>
            <input
              type="text"
              class="form-control"
              name="bopeScore"
              id="supplier-bopeScore"
              data-cy="bopeScore"
              :class="{ valid: !v$.bopeScore.$invalid, invalid: v$.bopeScore.$invalid }"
              v-model="v$.bopeScore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.internalSupplierId')"
              for="supplier-internalSupplierId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="internalSupplierId"
              id="supplier-internalSupplierId"
              data-cy="internalSupplierId"
              :class="{ valid: !v$.internalSupplierId.$invalid, invalid: v$.internalSupplierId.$invalid }"
              v-model="v$.internalSupplierId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.supplier.contact')" for="supplier-contact"></label>
            <input
              type="text"
              class="form-control"
              name="contact"
              id="supplier-contact"
              data-cy="contact"
              :class="{ valid: !v$.contact.$invalid, invalid: v$.contact.$invalid }"
              v-model="v$.contact.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.contactPhoneNumber')"
              for="supplier-contactPhoneNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="contactPhoneNumber"
              id="supplier-contactPhoneNumber"
              data-cy="contactPhoneNumber"
              :class="{ valid: !v$.contactPhoneNumber.$invalid, invalid: v$.contactPhoneNumber.$invalid }"
              v-model="v$.contactPhoneNumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.contactEmailAddress')"
              for="supplier-contactEmailAddress"
            ></label>
            <input
              type="text"
              class="form-control"
              name="contactEmailAddress"
              id="supplier-contactEmailAddress"
              data-cy="contactEmailAddress"
              :class="{ valid: !v$.contactEmailAddress.$invalid, invalid: v$.contactEmailAddress.$invalid }"
              v-model="v$.contactEmailAddress.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.operationSite')"
              for="supplier-operationSite"
            ></label>
            <input
              type="text"
              class="form-control"
              name="operationSite"
              id="supplier-operationSite"
              data-cy="operationSite"
              :class="{ valid: !v$.operationSite.$invalid, invalid: v$.operationSite.$invalid }"
              v-model="v$.operationSite.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.supplier.address')" for="supplier-address"></label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="supplier-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.supplier.website')" for="supplier-website"></label>
            <input
              type="text"
              class="form-control"
              name="website"
              id="supplier-website"
              data-cy="website"
              :class="{ valid: !v$.website.$invalid, invalid: v$.website.$invalid }"
              v-model="v$.website.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.relationStartingYear')"
              for="supplier-relationStartingYear"
            ></label>
            <input
              type="text"
              class="form-control"
              name="relationStartingYear"
              id="supplier-relationStartingYear"
              data-cy="relationStartingYear"
              :class="{ valid: !v$.relationStartingYear.$invalid, invalid: v$.relationStartingYear.$invalid }"
              v-model="v$.relationStartingYear.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.businessLicense')"
              for="supplier-businessLicense"
            ></label>
            <input
              type="text"
              class="form-control"
              name="businessLicense"
              id="supplier-businessLicense"
              data-cy="businessLicense"
              :class="{ valid: !v$.businessLicense.$invalid, invalid: v$.businessLicense.$invalid }"
              v-model="v$.businessLicense.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.rexOriginStatus')"
              for="supplier-rexOriginStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="rexOriginStatus"
              id="supplier-rexOriginStatus"
              data-cy="rexOriginStatus"
              :class="{ valid: !v$.rexOriginStatus.$invalid, invalid: v$.rexOriginStatus.$invalid }"
              v-model="v$.rexOriginStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.createDate')"
              for="supplier-createDate"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="supplier-createDate"
                  v-model="v$.createDate.$model"
                  name="createDate"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="supplier-createDate"
                data-cy="createDate"
                type="text"
                class="form-control"
                name="createDate"
                :class="{ valid: !v$.createDate.$invalid, invalid: v$.createDate.$invalid }"
                v-model="v$.createDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.updateDate')"
              for="supplier-updateDate"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="supplier-updateDate"
                  v-model="v$.updateDate.$model"
                  name="updateDate"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="supplier-updateDate"
                data-cy="updateDate"
                type="text"
                class="form-control"
                name="updateDate"
                :class="{ valid: !v$.updateDate.$invalid, invalid: v$.updateDate.$invalid }"
                v-model="v$.updateDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.supplier.item')" for="supplier-item"></label>
            <input
              type="text"
              class="form-control"
              name="item"
              id="supplier-item"
              data-cy="item"
              :class="{ valid: !v$.item.$invalid, invalid: v$.item.$invalid }"
              v-model="v$.item.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.supplier.subItems')" for="supplier-subItems"></label>
            <input
              type="text"
              class="form-control"
              name="subItems"
              id="supplier-subItems"
              data-cy="subItems"
              :class="{ valid: !v$.subItems.$invalid, invalid: v$.subItems.$invalid }"
              v-model="v$.subItems.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.supplier.date')" for="supplier-date"></label>
            <input
              type="text"
              class="form-control"
              name="date"
              id="supplier-date"
              data-cy="date"
              :class="{ valid: !v$.date.$invalid, invalid: v$.date.$invalid }"
              v-model="v$.date.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.kingdeeId')"
              for="supplier-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="supplier-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.supplier.region')" for="supplier-region"></label>
            <input
              type="text"
              class="form-control"
              name="region"
              id="supplier-region"
              data-cy="region"
              :class="{ valid: !v$.region.$invalid, invalid: v$.region.$invalid }"
              v-model="v$.region.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.supplierType')"
              for="supplier-supplierType"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplierType"
              id="supplier-supplierType"
              data-cy="supplierType"
              :class="{ valid: !v$.supplierType.$invalid, invalid: v$.supplierType.$invalid }"
              v-model="v$.supplierType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.supplier.remark')" for="supplier-remark"></label>
            <input
              type="text"
              class="form-control"
              name="remark"
              id="supplier-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplier.frenchName')"
              for="supplier-frenchName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="frenchName"
              id="supplier-frenchName"
              data-cy="frenchName"
              :class="{ valid: !v$.frenchName.$invalid, invalid: v$.frenchName.$invalid }"
              v-model="v$.frenchName.$model"
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
<script lang="ts" src="./supplier-update.component.ts"></script>
