<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.item.home.createOrEditLabel"
          data-cy="ItemCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.item.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="item.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="item.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.itemStatus')" for="item-itemStatus"></label>
            <input
              type="text"
              class="form-control"
              name="itemStatus"
              id="item-itemStatus"
              data-cy="itemStatus"
              :class="{ valid: !v$.itemStatus.$invalid, invalid: v$.itemStatus.$invalid }"
              v-model="v$.itemStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.itemFranceName')"
              for="item-itemFranceName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemFranceName"
              id="item-itemFranceName"
              data-cy="itemFranceName"
              :class="{ valid: !v$.itemFranceName.$invalid, invalid: v$.itemFranceName.$invalid }"
              v-model="v$.itemFranceName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.kingdeeId')" for="item-kingdeeId"></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="item-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.itemName')" for="item-itemName"></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="item-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.codeag')" for="item-codeag"></label>
            <input
              type="text"
              class="form-control"
              name="codeag"
              id="item-codeag"
              data-cy="codeag"
              :class="{ valid: !v$.codeag.$invalid, invalid: v$.codeag.$invalid }"
              v-model="v$.codeag.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.technicalDocuments')"
              for="item-technicalDocuments"
            ></label>
            <input
              type="text"
              class="form-control"
              name="technicalDocuments"
              id="item-technicalDocuments"
              data-cy="technicalDocuments"
              :class="{ valid: !v$.technicalDocuments.$invalid, invalid: v$.technicalDocuments.$invalid }"
              v-model="v$.technicalDocuments.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.certification')"
              for="item-certification"
            ></label>
            <input
              type="text"
              class="form-control"
              name="certification"
              id="item-certification"
              data-cy="certification"
              :class="{ valid: !v$.certification.$invalid, invalid: v$.certification.$invalid }"
              v-model="v$.certification.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.opportunitySheet')"
              for="item-opportunitySheet"
            ></label>
            <input
              type="text"
              class="form-control"
              name="opportunitySheet"
              id="item-opportunitySheet"
              data-cy="opportunitySheet"
              :class="{ valid: !v$.opportunitySheet.$invalid, invalid: v$.opportunitySheet.$invalid }"
              v-model="v$.opportunitySheet.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.packingType')" for="item-packingType"></label>
            <input
              type="text"
              class="form-control"
              name="packingType"
              id="item-packingType"
              data-cy="packingType"
              :class="{ valid: !v$.packingType.$invalid, invalid: v$.packingType.$invalid }"
              v-model="v$.packingType.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.salePackageImage')"
              for="item-salePackageImage"
            ></label>
            <input
              type="text"
              class="form-control"
              name="salePackageImage"
              id="item-salePackageImage"
              data-cy="salePackageImage"
              :class="{ valid: !v$.salePackageImage.$invalid, invalid: v$.salePackageImage.$invalid }"
              v-model="v$.salePackageImage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.cartonLengthMilimeter')"
              for="item-cartonLengthMilimeter"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cartonLengthMilimeter"
              id="item-cartonLengthMilimeter"
              data-cy="cartonLengthMilimeter"
              :class="{ valid: !v$.cartonLengthMilimeter.$invalid, invalid: v$.cartonLengthMilimeter.$invalid }"
              v-model="v$.cartonLengthMilimeter.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.cartonHeightMilimeter')"
              for="item-cartonHeightMilimeter"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cartonHeightMilimeter"
              id="item-cartonHeightMilimeter"
              data-cy="cartonHeightMilimeter"
              :class="{ valid: !v$.cartonHeightMilimeter.$invalid, invalid: v$.cartonHeightMilimeter.$invalid }"
              v-model="v$.cartonHeightMilimeter.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.barcode')" for="item-barcode"></label>
            <input
              type="text"
              class="form-control"
              name="barcode"
              id="item-barcode"
              data-cy="barcode"
              :class="{ valid: !v$.barcode.$invalid, invalid: v$.barcode.$invalid }"
              v-model="v$.barcode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.cartonWeightKg')"
              for="item-cartonWeightKg"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cartonWeightKg"
              id="item-cartonWeightKg"
              data-cy="cartonWeightKg"
              :class="{ valid: !v$.cartonWeightKg.$invalid, invalid: v$.cartonWeightKg.$invalid }"
              v-model="v$.cartonWeightKg.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.cartonWeightGr')"
              for="item-cartonWeightGr"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cartonWeightGr"
              id="item-cartonWeightGr"
              data-cy="cartonWeightGr"
              :class="{ valid: !v$.cartonWeightGr.$invalid, invalid: v$.cartonWeightGr.$invalid }"
              v-model="v$.cartonWeightGr.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.cartonWidthMilimeter')"
              for="item-cartonWidthMilimeter"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cartonWidthMilimeter"
              id="item-cartonWidthMilimeter"
              data-cy="cartonWidthMilimeter"
              :class="{ valid: !v$.cartonWidthMilimeter.$invalid, invalid: v$.cartonWidthMilimeter.$invalid }"
              v-model="v$.cartonWidthMilimeter.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.productDescriptionAndFonctionalities')"
              for="item-productDescriptionAndFonctionalities"
            ></label>
            <input
              type="text"
              class="form-control"
              name="productDescriptionAndFonctionalities"
              id="item-productDescriptionAndFonctionalities"
              data-cy="productDescriptionAndFonctionalities"
              :class="{
                valid: !v$.productDescriptionAndFonctionalities.$invalid,
                invalid: v$.productDescriptionAndFonctionalities.$invalid,
              }"
              v-model="v$.productDescriptionAndFonctionalities.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.drawing')" for="item-drawing"></label>
            <input
              type="text"
              class="form-control"
              name="drawing"
              id="item-drawing"
              data-cy="drawing"
              :class="{ valid: !v$.drawing.$invalid, invalid: v$.drawing.$invalid }"
              v-model="v$.drawing.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.userManual')" for="item-userManual"></label>
            <input
              type="text"
              class="form-control"
              name="userManual"
              id="item-userManual"
              data-cy="userManual"
              :class="{ valid: !v$.userManual.$invalid, invalid: v$.userManual.$invalid }"
              v-model="v$.userManual.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.palletSize')" for="item-palletSize"></label>
            <input
              type="text"
              class="form-control"
              name="palletSize"
              id="item-palletSize"
              data-cy="palletSize"
              :class="{ valid: !v$.palletSize.$invalid, invalid: v$.palletSize.$invalid }"
              v-model="v$.palletSize.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.typeOfMarketing')"
              for="item-typeOfMarketing"
            ></label>
            <input
              type="text"
              class="form-control"
              name="typeOfMarketing"
              id="item-typeOfMarketing"
              data-cy="typeOfMarketing"
              :class="{ valid: !v$.typeOfMarketing.$invalid, invalid: v$.typeOfMarketing.$invalid }"
              v-model="v$.typeOfMarketing.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.productPic')" for="item-productPic"></label>
            <input
              type="text"
              class="form-control"
              name="productPic"
              id="item-productPic"
              data-cy="productPic"
              :class="{ valid: !v$.productPic.$invalid, invalid: v$.productPic.$invalid }"
              v-model="v$.productPic.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.label')" for="item-label"></label>
            <input
              type="text"
              class="form-control"
              name="label"
              id="item-label"
              data-cy="label"
              :class="{ valid: !v$.label.$invalid, invalid: v$.label.$invalid }"
              v-model="v$.label.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.comment')" for="item-comment"></label>
            <input
              type="text"
              class="form-control"
              name="comment"
              id="item-comment"
              data-cy="comment"
              :class="{ valid: !v$.comment.$invalid, invalid: v$.comment.$invalid }"
              v-model="v$.comment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.productTaxonomy')"
              for="item-productTaxonomy"
            ></label>
            <input
              type="text"
              class="form-control"
              name="productTaxonomy"
              id="item-productTaxonomy"
              data-cy="productTaxonomy"
              :class="{ valid: !v$.productTaxonomy.$invalid, invalid: v$.productTaxonomy.$invalid }"
              v-model="v$.productTaxonomy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.netWeight')" for="item-netWeight"></label>
            <input
              type="text"
              class="form-control"
              name="netWeight"
              id="item-netWeight"
              data-cy="netWeight"
              :class="{ valid: !v$.netWeight.$invalid, invalid: v$.netWeight.$invalid }"
              v-model="v$.netWeight.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.grossWeight')" for="item-grossWeight"></label>
            <input
              type="text"
              class="form-control"
              name="grossWeight"
              id="item-grossWeight"
              data-cy="grossWeight"
              :class="{ valid: !v$.grossWeight.$invalid, invalid: v$.grossWeight.$invalid }"
              v-model="v$.grossWeight.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.item.unitOfWeight')" for="item-unitOfWeight"></label>
            <input
              type="text"
              class="form-control"
              name="unitOfWeight"
              id="item-unitOfWeight"
              data-cy="unitOfWeight"
              :class="{ valid: !v$.unitOfWeight.$invalid, invalid: v$.unitOfWeight.$invalid }"
              v-model="v$.unitOfWeight.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.item.cartonVolumeMilimeter')"
              for="item-cartonVolumeMilimeter"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cartonVolumeMilimeter"
              id="item-cartonVolumeMilimeter"
              data-cy="cartonVolumeMilimeter"
              :class="{ valid: !v$.cartonVolumeMilimeter.$invalid, invalid: v$.cartonVolumeMilimeter.$invalid }"
              v-model="v$.cartonVolumeMilimeter.$model"
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
<script lang="ts" src="./item-update.component.ts"></script>
