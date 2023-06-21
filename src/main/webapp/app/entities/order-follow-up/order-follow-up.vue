<template>
  <div>
    <h2 id="page-heading" data-cy="OrderFollowUpHeading">
      <span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.home.title')" id="order-follow-up-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'OrderFollowUpCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-order-follow-up"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && orderFollowUps && orderFollowUps.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="orderFollowUps && orderFollowUps.length > 0">
      <table class="table table-striped" aria-describedby="orderFollowUps">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.poNumber')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.supplier')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.orderDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.cateGory')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.inspectionDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.requestEndOfProdDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.totalAmount')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.totalDiscount')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.disCountRate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.regularCheck')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.etd')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.atd')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.eta')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.updatedEta')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.documentStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.customInstruction')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.customInspection')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.depositPaymentDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.balanceOfTotalPaymentDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.amountDepositPayment')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.amountPayment')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.dcMember')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.comment')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.kingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.parentKingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.qty')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.itemCode')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.contractEndOfProdDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.orderFollowUp.supplierId')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="orderFollowUp in orderFollowUps" :key="orderFollowUp.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'OrderFollowUpView', params: { orderFollowUpId: orderFollowUp.id } }">{{
                orderFollowUp.id
              }}</router-link>
            </td>
            <td>{{ orderFollowUp.poNumber }}</td>
            <td>{{ orderFollowUp.supplier }}</td>
            <td>{{ orderFollowUp.orderDate }}</td>
            <td>{{ orderFollowUp.cateGory }}</td>
            <td>{{ orderFollowUp.inspectionDate }}</td>
            <td>{{ orderFollowUp.requestEndOfProdDate }}</td>
            <td>{{ orderFollowUp.totalAmount }}</td>
            <td>{{ orderFollowUp.totalDiscount }}</td>
            <td>{{ orderFollowUp.disCountRate }}</td>
            <td>{{ orderFollowUp.regularCheck }}</td>
            <td>{{ orderFollowUp.etd }}</td>
            <td>{{ orderFollowUp.atd }}</td>
            <td>{{ orderFollowUp.eta }}</td>
            <td>{{ orderFollowUp.updatedEta }}</td>
            <td>{{ orderFollowUp.documentStatus }}</td>
            <td>{{ orderFollowUp.customInstruction }}</td>
            <td>{{ orderFollowUp.customInspection }}</td>
            <td>{{ orderFollowUp.depositPaymentDate }}</td>
            <td>{{ orderFollowUp.balanceOfTotalPaymentDate }}</td>
            <td>{{ orderFollowUp.amountDepositPayment }}</td>
            <td>{{ orderFollowUp.amountPayment }}</td>
            <td>{{ orderFollowUp.dcMember }}</td>
            <td>{{ orderFollowUp.comment }}</td>
            <td>{{ orderFollowUp.itemName }}</td>
            <td>{{ orderFollowUp.kingdeeId }}</td>
            <td>{{ orderFollowUp.parentKingdeeId }}</td>
            <td>{{ orderFollowUp.qty }}</td>
            <td>{{ orderFollowUp.itemCode }}</td>
            <td>{{ orderFollowUp.contractEndOfProdDate }}</td>
            <td>{{ orderFollowUp.supplierId }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OrderFollowUpView', params: { orderFollowUpId: orderFollowUp.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OrderFollowUpEdit', params: { orderFollowUpId: orderFollowUp.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(orderFollowUp)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="descoursCabaudApplicationApp.orderFollowUp.delete.question"
          data-cy="orderFollowUpDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-orderFollowUp-heading"
          v-text="t$('descoursCabaudApplicationApp.orderFollowUp.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-orderFollowUp"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOrderFollowUp()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./order-follow-up.component.ts"></script>
