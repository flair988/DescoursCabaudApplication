<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.forwarderBooking.home.createOrEditLabel"
          data-cy="ForwarderBookingCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.forwarderBooking.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="forwarderBooking.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="forwarderBooking.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.itemName')"
              for="forwarder-booking-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="forwarder-booking-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.kingdeeId')"
              for="forwarder-booking-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="forwarder-booking-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.customer')"
              for="forwarder-booking-customer"
            ></label>
            <input
              type="text"
              class="form-control"
              name="customer"
              id="forwarder-booking-customer"
              data-cy="customer"
              :class="{ valid: !v$.customer.$invalid, invalid: v$.customer.$invalid }"
              v-model="v$.customer.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.orderDate')"
              for="forwarder-booking-orderDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="orderDate"
              id="forwarder-booking-orderDate"
              data-cy="orderDate"
              :class="{ valid: !v$.orderDate.$invalid, invalid: v$.orderDate.$invalid }"
              v-model="v$.orderDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.forwarder')"
              for="forwarder-booking-forwarder"
            ></label>
            <input
              type="text"
              class="form-control"
              name="forwarder"
              id="forwarder-booking-forwarder"
              data-cy="forwarder"
              :class="{ valid: !v$.forwarder.$invalid, invalid: v$.forwarder.$invalid }"
              v-model="v$.forwarder.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.totalQty')"
              for="forwarder-booking-totalQty"
            ></label>
            <input
              type="text"
              class="form-control"
              name="totalQty"
              id="forwarder-booking-totalQty"
              data-cy="totalQty"
              :class="{ valid: !v$.totalQty.$invalid, invalid: v$.totalQty.$invalid }"
              v-model="v$.totalQty.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.loadingPort')"
              for="forwarder-booking-loadingPort"
            ></label>
            <input
              type="text"
              class="form-control"
              name="loadingPort"
              id="forwarder-booking-loadingPort"
              data-cy="loadingPort"
              :class="{ valid: !v$.loadingPort.$invalid, invalid: v$.loadingPort.$invalid }"
              v-model="v$.loadingPort.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.dischargePort')"
              for="forwarder-booking-dischargePort"
            ></label>
            <input
              type="text"
              class="form-control"
              name="dischargePort"
              id="forwarder-booking-dischargePort"
              data-cy="dischargePort"
              :class="{ valid: !v$.dischargePort.$invalid, invalid: v$.dischargePort.$invalid }"
              v-model="v$.dischargePort.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.containerType')"
              for="forwarder-booking-containerType"
            ></label>
            <input
              type="text"
              class="form-control"
              name="containerType"
              id="forwarder-booking-containerType"
              data-cy="containerType"
              :class="{ valid: !v$.containerType.$invalid, invalid: v$.containerType.$invalid }"
              v-model="v$.containerType.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.containerSize')"
              for="forwarder-booking-containerSize"
            ></label>
            <input
              type="text"
              class="form-control"
              name="containerSize"
              id="forwarder-booking-containerSize"
              data-cy="containerSize"
              :class="{ valid: !v$.containerSize.$invalid, invalid: v$.containerSize.$invalid }"
              v-model="v$.containerSize.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.containerNumber')"
              for="forwarder-booking-containerNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="containerNumber"
              id="forwarder-booking-containerNumber"
              data-cy="containerNumber"
              :class="{ valid: !v$.containerNumber.$invalid, invalid: v$.containerNumber.$invalid }"
              v-model="v$.containerNumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.supplier')"
              for="forwarder-booking-supplier"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplier"
              id="forwarder-booking-supplier"
              data-cy="supplier"
              :class="{ valid: !v$.supplier.$invalid, invalid: v$.supplier.$invalid }"
              v-model="v$.supplier.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.supplierEmail')"
              for="forwarder-booking-supplierEmail"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplierEmail"
              id="forwarder-booking-supplierEmail"
              data-cy="supplierEmail"
              :class="{ valid: !v$.supplierEmail.$invalid, invalid: v$.supplierEmail.$invalid }"
              v-model="v$.supplierEmail.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.eta')"
              for="forwarder-booking-eta"
            ></label>
            <input
              type="text"
              class="form-control"
              name="eta"
              id="forwarder-booking-eta"
              data-cy="eta"
              :class="{ valid: !v$.eta.$invalid, invalid: v$.eta.$invalid }"
              v-model="v$.eta.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.etd')"
              for="forwarder-booking-etd"
            ></label>
            <input
              type="text"
              class="form-control"
              name="etd"
              id="forwarder-booking-etd"
              data-cy="etd"
              :class="{ valid: !v$.etd.$invalid, invalid: v$.etd.$invalid }"
              v-model="v$.etd.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.transportMode')"
              for="forwarder-booking-transportMode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transportMode"
              id="forwarder-booking-transportMode"
              data-cy="transportMode"
              :class="{ valid: !v$.transportMode.$invalid, invalid: v$.transportMode.$invalid }"
              v-model="v$.transportMode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.numberOfCartons')"
              for="forwarder-booking-numberOfCartons"
            ></label>
            <input
              type="text"
              class="form-control"
              name="numberOfCartons"
              id="forwarder-booking-numberOfCartons"
              data-cy="numberOfCartons"
              :class="{ valid: !v$.numberOfCartons.$invalid, invalid: v$.numberOfCartons.$invalid }"
              v-model="v$.numberOfCartons.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.numberOfRef')"
              for="forwarder-booking-numberOfRef"
            ></label>
            <input
              type="text"
              class="form-control"
              name="numberOfRef"
              id="forwarder-booking-numberOfRef"
              data-cy="numberOfRef"
              :class="{ valid: !v$.numberOfRef.$invalid, invalid: v$.numberOfRef.$invalid }"
              v-model="v$.numberOfRef.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.totalVolume')"
              for="forwarder-booking-totalVolume"
            ></label>
            <input
              type="text"
              class="form-control"
              name="totalVolume"
              id="forwarder-booking-totalVolume"
              data-cy="totalVolume"
              :class="{ valid: !v$.totalVolume.$invalid, invalid: v$.totalVolume.$invalid }"
              v-model="v$.totalVolume.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.totalWeight')"
              for="forwarder-booking-totalWeight"
            ></label>
            <input
              type="text"
              class="form-control"
              name="totalWeight"
              id="forwarder-booking-totalWeight"
              data-cy="totalWeight"
              :class="{ valid: !v$.totalWeight.$invalid, invalid: v$.totalWeight.$invalid }"
              v-model="v$.totalWeight.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.remark')"
              for="forwarder-booking-remark"
            ></label>
            <input
              type="text"
              class="form-control"
              name="remark"
              id="forwarder-booking-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.client')"
              for="forwarder-booking-client"
            ></label>
            <input
              type="text"
              class="form-control"
              name="client"
              id="forwarder-booking-client"
              data-cy="client"
              :class="{ valid: !v$.client.$invalid, invalid: v$.client.$invalid }"
              v-model="v$.client.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarderBooking.kingdeeUniqueId')"
              for="forwarder-booking-kingdeeUniqueId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeUniqueId"
              id="forwarder-booking-kingdeeUniqueId"
              data-cy="kingdeeUniqueId"
              :class="{ valid: !v$.kingdeeUniqueId.$invalid, invalid: v$.kingdeeUniqueId.$invalid }"
              v-model="v$.kingdeeUniqueId.$model"
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
<script lang="ts" src="./forwarder-booking-update.component.ts"></script>
