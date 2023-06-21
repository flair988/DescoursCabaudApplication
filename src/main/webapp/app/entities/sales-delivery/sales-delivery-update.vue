<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.salesDelivery.home.createOrEditLabel"
          data-cy="SalesDeliveryCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.salesDelivery.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="salesDelivery.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="salesDelivery.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.itemName')"
              for="sales-delivery-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="sales-delivery-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.kingdeeId')"
              for="sales-delivery-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="sales-delivery-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.customer')"
              for="sales-delivery-customer"
            ></label>
            <input
              type="text"
              class="form-control"
              name="customer"
              id="sales-delivery-customer"
              data-cy="customer"
              :class="{ valid: !v$.customer.$invalid, invalid: v$.customer.$invalid }"
              v-model="v$.customer.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.orderDate')"
              for="sales-delivery-orderDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="orderDate"
              id="sales-delivery-orderDate"
              data-cy="orderDate"
              :class="{ valid: !v$.orderDate.$invalid, invalid: v$.orderDate.$invalid }"
              v-model="v$.orderDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.loadingPort')"
              for="sales-delivery-loadingPort"
            ></label>
            <input
              type="text"
              class="form-control"
              name="loadingPort"
              id="sales-delivery-loadingPort"
              data-cy="loadingPort"
              :class="{ valid: !v$.loadingPort.$invalid, invalid: v$.loadingPort.$invalid }"
              v-model="v$.loadingPort.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.dischargePort')"
              for="sales-delivery-dischargePort"
            ></label>
            <input
              type="text"
              class="form-control"
              name="dischargePort"
              id="sales-delivery-dischargePort"
              data-cy="dischargePort"
              :class="{ valid: !v$.dischargePort.$invalid, invalid: v$.dischargePort.$invalid }"
              v-model="v$.dischargePort.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.transportMode')"
              for="sales-delivery-transportMode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="transportMode"
              id="sales-delivery-transportMode"
              data-cy="transportMode"
              :class="{ valid: !v$.transportMode.$invalid, invalid: v$.transportMode.$invalid }"
              v-model="v$.transportMode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.incoterm')"
              for="sales-delivery-incoterm"
            ></label>
            <input
              type="text"
              class="form-control"
              name="incoterm"
              id="sales-delivery-incoterm"
              data-cy="incoterm"
              :class="{ valid: !v$.incoterm.$invalid, invalid: v$.incoterm.$invalid }"
              v-model="v$.incoterm.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.forwarder')"
              for="sales-delivery-forwarder"
            ></label>
            <input
              type="text"
              class="form-control"
              name="forwarder"
              id="sales-delivery-forwarder"
              data-cy="forwarder"
              :class="{ valid: !v$.forwarder.$invalid, invalid: v$.forwarder.$invalid }"
              v-model="v$.forwarder.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.eta')"
              for="sales-delivery-eta"
            ></label>
            <input
              type="text"
              class="form-control"
              name="eta"
              id="sales-delivery-eta"
              data-cy="eta"
              :class="{ valid: !v$.eta.$invalid, invalid: v$.eta.$invalid }"
              v-model="v$.eta.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.etd')"
              for="sales-delivery-etd"
            ></label>
            <input
              type="text"
              class="form-control"
              name="etd"
              id="sales-delivery-etd"
              data-cy="etd"
              :class="{ valid: !v$.etd.$invalid, invalid: v$.etd.$invalid }"
              v-model="v$.etd.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.containerType')"
              for="sales-delivery-containerType"
            ></label>
            <input
              type="text"
              class="form-control"
              name="containerType"
              id="sales-delivery-containerType"
              data-cy="containerType"
              :class="{ valid: !v$.containerType.$invalid, invalid: v$.containerType.$invalid }"
              v-model="v$.containerType.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.containerSize')"
              for="sales-delivery-containerSize"
            ></label>
            <input
              type="text"
              class="form-control"
              name="containerSize"
              id="sales-delivery-containerSize"
              data-cy="containerSize"
              :class="{ valid: !v$.containerSize.$invalid, invalid: v$.containerSize.$invalid }"
              v-model="v$.containerSize.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.remark')"
              for="sales-delivery-remark"
            ></label>
            <input
              type="text"
              class="form-control"
              name="remark"
              id="sales-delivery-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.kingdeeUniqueId')"
              for="sales-delivery-kingdeeUniqueId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeUniqueId"
              id="sales-delivery-kingdeeUniqueId"
              data-cy="kingdeeUniqueId"
              :class="{ valid: !v$.kingdeeUniqueId.$invalid, invalid: v$.kingdeeUniqueId.$invalid }"
              v-model="v$.kingdeeUniqueId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.docStatus')"
              for="sales-delivery-docStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="docStatus"
              id="sales-delivery-docStatus"
              data-cy="docStatus"
              :class="{ valid: !v$.docStatus.$invalid, invalid: v$.docStatus.$invalid }"
              v-model="v$.docStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.salesDelivery.cateGory')"
              for="sales-delivery-cateGory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cateGory"
              id="sales-delivery-cateGory"
              data-cy="cateGory"
              :class="{ valid: !v$.cateGory.$invalid, invalid: v$.cateGory.$invalid }"
              v-model="v$.cateGory.$model"
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
<script lang="ts" src="./sales-delivery-update.component.ts"></script>
