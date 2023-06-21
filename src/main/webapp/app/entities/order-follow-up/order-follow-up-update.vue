<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.orderFollowUp.home.createOrEditLabel"
          data-cy="OrderFollowUpCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.orderFollowUp.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="orderFollowUp.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="orderFollowUp.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.poNumber')"
              for="order-follow-up-poNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="poNumber"
              id="order-follow-up-poNumber"
              data-cy="poNumber"
              :class="{ valid: !v$.poNumber.$invalid, invalid: v$.poNumber.$invalid }"
              v-model="v$.poNumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.supplier')"
              for="order-follow-up-supplier"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplier"
              id="order-follow-up-supplier"
              data-cy="supplier"
              :class="{ valid: !v$.supplier.$invalid, invalid: v$.supplier.$invalid }"
              v-model="v$.supplier.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.orderDate')"
              for="order-follow-up-orderDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="orderDate"
              id="order-follow-up-orderDate"
              data-cy="orderDate"
              :class="{ valid: !v$.orderDate.$invalid, invalid: v$.orderDate.$invalid }"
              v-model="v$.orderDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.cateGory')"
              for="order-follow-up-cateGory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cateGory"
              id="order-follow-up-cateGory"
              data-cy="cateGory"
              :class="{ valid: !v$.cateGory.$invalid, invalid: v$.cateGory.$invalid }"
              v-model="v$.cateGory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.inspectionDate')"
              for="order-follow-up-inspectionDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="inspectionDate"
              id="order-follow-up-inspectionDate"
              data-cy="inspectionDate"
              :class="{ valid: !v$.inspectionDate.$invalid, invalid: v$.inspectionDate.$invalid }"
              v-model="v$.inspectionDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.requestEndOfProdDate')"
              for="order-follow-up-requestEndOfProdDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="requestEndOfProdDate"
              id="order-follow-up-requestEndOfProdDate"
              data-cy="requestEndOfProdDate"
              :class="{ valid: !v$.requestEndOfProdDate.$invalid, invalid: v$.requestEndOfProdDate.$invalid }"
              v-model="v$.requestEndOfProdDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.totalAmount')"
              for="order-follow-up-totalAmount"
            ></label>
            <input
              type="text"
              class="form-control"
              name="totalAmount"
              id="order-follow-up-totalAmount"
              data-cy="totalAmount"
              :class="{ valid: !v$.totalAmount.$invalid, invalid: v$.totalAmount.$invalid }"
              v-model="v$.totalAmount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.totalDiscount')"
              for="order-follow-up-totalDiscount"
            ></label>
            <input
              type="text"
              class="form-control"
              name="totalDiscount"
              id="order-follow-up-totalDiscount"
              data-cy="totalDiscount"
              :class="{ valid: !v$.totalDiscount.$invalid, invalid: v$.totalDiscount.$invalid }"
              v-model="v$.totalDiscount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.disCountRate')"
              for="order-follow-up-disCountRate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="disCountRate"
              id="order-follow-up-disCountRate"
              data-cy="disCountRate"
              :class="{ valid: !v$.disCountRate.$invalid, invalid: v$.disCountRate.$invalid }"
              v-model="v$.disCountRate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.regularCheck')"
              for="order-follow-up-regularCheck"
            ></label>
            <input
              type="text"
              class="form-control"
              name="regularCheck"
              id="order-follow-up-regularCheck"
              data-cy="regularCheck"
              :class="{ valid: !v$.regularCheck.$invalid, invalid: v$.regularCheck.$invalid }"
              v-model="v$.regularCheck.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.etd')"
              for="order-follow-up-etd"
            ></label>
            <input
              type="text"
              class="form-control"
              name="etd"
              id="order-follow-up-etd"
              data-cy="etd"
              :class="{ valid: !v$.etd.$invalid, invalid: v$.etd.$invalid }"
              v-model="v$.etd.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.atd')"
              for="order-follow-up-atd"
            ></label>
            <input
              type="text"
              class="form-control"
              name="atd"
              id="order-follow-up-atd"
              data-cy="atd"
              :class="{ valid: !v$.atd.$invalid, invalid: v$.atd.$invalid }"
              v-model="v$.atd.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.eta')"
              for="order-follow-up-eta"
            ></label>
            <input
              type="text"
              class="form-control"
              name="eta"
              id="order-follow-up-eta"
              data-cy="eta"
              :class="{ valid: !v$.eta.$invalid, invalid: v$.eta.$invalid }"
              v-model="v$.eta.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.updatedEta')"
              for="order-follow-up-updatedEta"
            ></label>
            <input
              type="text"
              class="form-control"
              name="updatedEta"
              id="order-follow-up-updatedEta"
              data-cy="updatedEta"
              :class="{ valid: !v$.updatedEta.$invalid, invalid: v$.updatedEta.$invalid }"
              v-model="v$.updatedEta.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.documentStatus')"
              for="order-follow-up-documentStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="documentStatus"
              id="order-follow-up-documentStatus"
              data-cy="documentStatus"
              :class="{ valid: !v$.documentStatus.$invalid, invalid: v$.documentStatus.$invalid }"
              v-model="v$.documentStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.customInstruction')"
              for="order-follow-up-customInstruction"
            ></label>
            <input
              type="text"
              class="form-control"
              name="customInstruction"
              id="order-follow-up-customInstruction"
              data-cy="customInstruction"
              :class="{ valid: !v$.customInstruction.$invalid, invalid: v$.customInstruction.$invalid }"
              v-model="v$.customInstruction.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.customInspection')"
              for="order-follow-up-customInspection"
            ></label>
            <input
              type="text"
              class="form-control"
              name="customInspection"
              id="order-follow-up-customInspection"
              data-cy="customInspection"
              :class="{ valid: !v$.customInspection.$invalid, invalid: v$.customInspection.$invalid }"
              v-model="v$.customInspection.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.depositPaymentDate')"
              for="order-follow-up-depositPaymentDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="depositPaymentDate"
              id="order-follow-up-depositPaymentDate"
              data-cy="depositPaymentDate"
              :class="{ valid: !v$.depositPaymentDate.$invalid, invalid: v$.depositPaymentDate.$invalid }"
              v-model="v$.depositPaymentDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.balanceOfTotalPaymentDate')"
              for="order-follow-up-balanceOfTotalPaymentDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="balanceOfTotalPaymentDate"
              id="order-follow-up-balanceOfTotalPaymentDate"
              data-cy="balanceOfTotalPaymentDate"
              :class="{ valid: !v$.balanceOfTotalPaymentDate.$invalid, invalid: v$.balanceOfTotalPaymentDate.$invalid }"
              v-model="v$.balanceOfTotalPaymentDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.amountDepositPayment')"
              for="order-follow-up-amountDepositPayment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="amountDepositPayment"
              id="order-follow-up-amountDepositPayment"
              data-cy="amountDepositPayment"
              :class="{ valid: !v$.amountDepositPayment.$invalid, invalid: v$.amountDepositPayment.$invalid }"
              v-model="v$.amountDepositPayment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.amountPayment')"
              for="order-follow-up-amountPayment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="amountPayment"
              id="order-follow-up-amountPayment"
              data-cy="amountPayment"
              :class="{ valid: !v$.amountPayment.$invalid, invalid: v$.amountPayment.$invalid }"
              v-model="v$.amountPayment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.dcMember')"
              for="order-follow-up-dcMember"
            ></label>
            <input
              type="text"
              class="form-control"
              name="dcMember"
              id="order-follow-up-dcMember"
              data-cy="dcMember"
              :class="{ valid: !v$.dcMember.$invalid, invalid: v$.dcMember.$invalid }"
              v-model="v$.dcMember.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.comment')"
              for="order-follow-up-comment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="comment"
              id="order-follow-up-comment"
              data-cy="comment"
              :class="{ valid: !v$.comment.$invalid, invalid: v$.comment.$invalid }"
              v-model="v$.comment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.itemName')"
              for="order-follow-up-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="order-follow-up-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.kingdeeId')"
              for="order-follow-up-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="order-follow-up-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.parentKingdeeId')"
              for="order-follow-up-parentKingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="parentKingdeeId"
              id="order-follow-up-parentKingdeeId"
              data-cy="parentKingdeeId"
              :class="{ valid: !v$.parentKingdeeId.$invalid, invalid: v$.parentKingdeeId.$invalid }"
              v-model="v$.parentKingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.qty')"
              for="order-follow-up-qty"
            ></label>
            <input
              type="text"
              class="form-control"
              name="qty"
              id="order-follow-up-qty"
              data-cy="qty"
              :class="{ valid: !v$.qty.$invalid, invalid: v$.qty.$invalid }"
              v-model="v$.qty.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.itemCode')"
              for="order-follow-up-itemCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemCode"
              id="order-follow-up-itemCode"
              data-cy="itemCode"
              :class="{ valid: !v$.itemCode.$invalid, invalid: v$.itemCode.$invalid }"
              v-model="v$.itemCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.contractEndOfProdDate')"
              for="order-follow-up-contractEndOfProdDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="contractEndOfProdDate"
              id="order-follow-up-contractEndOfProdDate"
              data-cy="contractEndOfProdDate"
              :class="{ valid: !v$.contractEndOfProdDate.$invalid, invalid: v$.contractEndOfProdDate.$invalid }"
              v-model="v$.contractEndOfProdDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.orderFollowUp.supplierId')"
              for="order-follow-up-supplierId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplierId"
              id="order-follow-up-supplierId"
              data-cy="supplierId"
              :class="{ valid: !v$.supplierId.$invalid, invalid: v$.supplierId.$invalid }"
              v-model="v$.supplierId.$model"
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
<script lang="ts" src="./order-follow-up-update.component.ts"></script>
