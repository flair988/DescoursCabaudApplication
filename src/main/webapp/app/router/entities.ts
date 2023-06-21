import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

const OPSiteQualification = () => import('@/entities/op-site-qualification/op-site-qualification.vue');
const OPSiteQualificationUpdate = () => import('@/entities/op-site-qualification/op-site-qualification-update.vue');
const OPSiteQualificationDetails = () => import('@/entities/op-site-qualification/op-site-qualification-details.vue');

const PI = () => import('@/entities/pi/pi.vue');
const PIUpdate = () => import('@/entities/pi/pi-update.vue');
const PIDetails = () => import('@/entities/pi/pi-details.vue');

const MondayUser = () => import('@/entities/monday-user/monday-user.vue');
const MondayUserUpdate = () => import('@/entities/monday-user/monday-user-update.vue');
const MondayUserDetails = () => import('@/entities/monday-user/monday-user-details.vue');

const ProductFinished = () => import('@/entities/product-finished/product-finished.vue');
const ProductFinishedUpdate = () => import('@/entities/product-finished/product-finished-update.vue');
const ProductFinishedDetails = () => import('@/entities/product-finished/product-finished-details.vue');

const Supplier = () => import('@/entities/supplier/supplier.vue');
const SupplierUpdate = () => import('@/entities/supplier/supplier-update.vue');
const SupplierDetails = () => import('@/entities/supplier/supplier-details.vue');

const Item = () => import('@/entities/item/item.vue');
const ItemUpdate = () => import('@/entities/item/item-update.vue');
const ItemDetails = () => import('@/entities/item/item-details.vue');

const PurchaseReturn = () => import('@/entities/purchase-return/purchase-return.vue');
const PurchaseReturnUpdate = () => import('@/entities/purchase-return/purchase-return-update.vue');
const PurchaseReturnDetails = () => import('@/entities/purchase-return/purchase-return-details.vue');

const ProcessLog = () => import('@/entities/process-log/process-log.vue');
const ProcessLogUpdate = () => import('@/entities/process-log/process-log-update.vue');
const ProcessLogDetails = () => import('@/entities/process-log/process-log-details.vue');

const Quotation = () => import('@/entities/quotation/quotation.vue');
const QuotationUpdate = () => import('@/entities/quotation/quotation-update.vue');
const QuotationDetails = () => import('@/entities/quotation/quotation-details.vue');

const OrderFollowUp = () => import('@/entities/order-follow-up/order-follow-up.vue');
const OrderFollowUpUpdate = () => import('@/entities/order-follow-up/order-follow-up-update.vue');
const OrderFollowUpDetails = () => import('@/entities/order-follow-up/order-follow-up-details.vue');

const OperationSiteAuditing = () => import('@/entities/operation-site-auditing/operation-site-auditing.vue');
const OperationSiteAuditingUpdate = () => import('@/entities/operation-site-auditing/operation-site-auditing-update.vue');
const OperationSiteAuditingDetails = () => import('@/entities/operation-site-auditing/operation-site-auditing-details.vue');

const SupplierQualification = () => import('@/entities/supplier-qualification/supplier-qualification.vue');
const SupplierQualificationUpdate = () => import('@/entities/supplier-qualification/supplier-qualification-update.vue');
const SupplierQualificationDetails = () => import('@/entities/supplier-qualification/supplier-qualification-details.vue');

const Uom = () => import('@/entities/uom/uom.vue');
const UomUpdate = () => import('@/entities/uom/uom-update.vue');
const UomDetails = () => import('@/entities/uom/uom-details.vue');

const ForwarderBooking = () => import('@/entities/forwarder-booking/forwarder-booking.vue');
const ForwarderBookingUpdate = () => import('@/entities/forwarder-booking/forwarder-booking-update.vue');
const ForwarderBookingDetails = () => import('@/entities/forwarder-booking/forwarder-booking-details.vue');

const ProductTaxmonomy = () => import('@/entities/product-taxmonomy/product-taxmonomy.vue');
const ProductTaxmonomyUpdate = () => import('@/entities/product-taxmonomy/product-taxmonomy-update.vue');
const ProductTaxmonomyDetails = () => import('@/entities/product-taxmonomy/product-taxmonomy-details.vue');

const CommercialInvoice = () => import('@/entities/commercial-invoice/commercial-invoice.vue');
const CommercialInvoiceUpdate = () => import('@/entities/commercial-invoice/commercial-invoice-update.vue');
const CommercialInvoiceDetails = () => import('@/entities/commercial-invoice/commercial-invoice-details.vue');

const Forwarder = () => import('@/entities/forwarder/forwarder.vue');
const ForwarderUpdate = () => import('@/entities/forwarder/forwarder-update.vue');
const ForwarderDetails = () => import('@/entities/forwarder/forwarder-details.vue');

const Inspection = () => import('@/entities/inspection/inspection.vue');
const InspectionUpdate = () => import('@/entities/inspection/inspection-update.vue');
const InspectionDetails = () => import('@/entities/inspection/inspection-details.vue');

const OperationSite = () => import('@/entities/operation-site/operation-site.vue');
const OperationSiteUpdate = () => import('@/entities/operation-site/operation-site-update.vue');
const OperationSiteDetails = () => import('@/entities/operation-site/operation-site-details.vue');

const Group = () => import('@/entities/group/group.vue');
const GroupUpdate = () => import('@/entities/group/group-update.vue');
const GroupDetails = () => import('@/entities/group/group-details.vue');

const SalesDelivery = () => import('@/entities/sales-delivery/sales-delivery.vue');
const SalesDeliveryUpdate = () => import('@/entities/sales-delivery/sales-delivery-update.vue');
const SalesDeliveryDetails = () => import('@/entities/sales-delivery/sales-delivery-details.vue');

const Client = () => import('@/entities/client/client.vue');
const ClientUpdate = () => import('@/entities/client/client-update.vue');
const ClientDetails = () => import('@/entities/client/client-details.vue');

const MondayColumn = () => import('@/entities/monday-column/monday-column.vue');
const MondayColumnUpdate = () => import('@/entities/monday-column/monday-column-update.vue');
const MondayColumnDetails = () => import('@/entities/monday-column/monday-column-details.vue');

const Authority = () => import('@/entities/authority/authority.vue');
const AuthorityUpdate = () => import('@/entities/authority/authority-update.vue');
const AuthorityDetails = () => import('@/entities/authority/authority-details.vue');

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'op-site-qualification',
      name: 'OPSiteQualification',
      component: OPSiteQualification,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'op-site-qualification/new',
      name: 'OPSiteQualificationCreate',
      component: OPSiteQualificationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'op-site-qualification/:oPSiteQualificationId/edit',
      name: 'OPSiteQualificationEdit',
      component: OPSiteQualificationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'op-site-qualification/:oPSiteQualificationId/view',
      name: 'OPSiteQualificationView',
      component: OPSiteQualificationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pi',
      name: 'PI',
      component: PI,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pi/new',
      name: 'PICreate',
      component: PIUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pi/:pIId/edit',
      name: 'PIEdit',
      component: PIUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'pi/:pIId/view',
      name: 'PIView',
      component: PIDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monday-user',
      name: 'MondayUser',
      component: MondayUser,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monday-user/new',
      name: 'MondayUserCreate',
      component: MondayUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monday-user/:mondayUserId/edit',
      name: 'MondayUserEdit',
      component: MondayUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monday-user/:mondayUserId/view',
      name: 'MondayUserView',
      component: MondayUserDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-finished',
      name: 'ProductFinished',
      component: ProductFinished,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-finished/new',
      name: 'ProductFinishedCreate',
      component: ProductFinishedUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-finished/:productFinishedId/edit',
      name: 'ProductFinishedEdit',
      component: ProductFinishedUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-finished/:productFinishedId/view',
      name: 'ProductFinishedView',
      component: ProductFinishedDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'supplier',
      name: 'Supplier',
      component: Supplier,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'supplier/new',
      name: 'SupplierCreate',
      component: SupplierUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'supplier/:supplierId/edit',
      name: 'SupplierEdit',
      component: SupplierUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'supplier/:supplierId/view',
      name: 'SupplierView',
      component: SupplierDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'item',
      name: 'Item',
      component: Item,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'item/new',
      name: 'ItemCreate',
      component: ItemUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'item/:itemId/edit',
      name: 'ItemEdit',
      component: ItemUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'item/:itemId/view',
      name: 'ItemView',
      component: ItemDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'purchase-return',
      name: 'PurchaseReturn',
      component: PurchaseReturn,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'purchase-return/new',
      name: 'PurchaseReturnCreate',
      component: PurchaseReturnUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'purchase-return/:purchaseReturnId/edit',
      name: 'PurchaseReturnEdit',
      component: PurchaseReturnUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'purchase-return/:purchaseReturnId/view',
      name: 'PurchaseReturnView',
      component: PurchaseReturnDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'process-log',
      name: 'ProcessLog',
      component: ProcessLog,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'process-log/new',
      name: 'ProcessLogCreate',
      component: ProcessLogUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'process-log/:processLogId/edit',
      name: 'ProcessLogEdit',
      component: ProcessLogUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'process-log/:processLogId/view',
      name: 'ProcessLogView',
      component: ProcessLogDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quotation',
      name: 'Quotation',
      component: Quotation,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quotation/new',
      name: 'QuotationCreate',
      component: QuotationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quotation/:quotationId/edit',
      name: 'QuotationEdit',
      component: QuotationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'quotation/:quotationId/view',
      name: 'QuotationView',
      component: QuotationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order-follow-up',
      name: 'OrderFollowUp',
      component: OrderFollowUp,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order-follow-up/new',
      name: 'OrderFollowUpCreate',
      component: OrderFollowUpUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order-follow-up/:orderFollowUpId/edit',
      name: 'OrderFollowUpEdit',
      component: OrderFollowUpUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'order-follow-up/:orderFollowUpId/view',
      name: 'OrderFollowUpView',
      component: OrderFollowUpDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'operation-site-auditing',
      name: 'OperationSiteAuditing',
      component: OperationSiteAuditing,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'operation-site-auditing/new',
      name: 'OperationSiteAuditingCreate',
      component: OperationSiteAuditingUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'operation-site-auditing/:operationSiteAuditingId/edit',
      name: 'OperationSiteAuditingEdit',
      component: OperationSiteAuditingUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'operation-site-auditing/:operationSiteAuditingId/view',
      name: 'OperationSiteAuditingView',
      component: OperationSiteAuditingDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'supplier-qualification',
      name: 'SupplierQualification',
      component: SupplierQualification,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'supplier-qualification/new',
      name: 'SupplierQualificationCreate',
      component: SupplierQualificationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'supplier-qualification/:supplierQualificationId/edit',
      name: 'SupplierQualificationEdit',
      component: SupplierQualificationUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'supplier-qualification/:supplierQualificationId/view',
      name: 'SupplierQualificationView',
      component: SupplierQualificationDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uom',
      name: 'Uom',
      component: Uom,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uom/new',
      name: 'UomCreate',
      component: UomUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uom/:uomId/edit',
      name: 'UomEdit',
      component: UomUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'uom/:uomId/view',
      name: 'UomView',
      component: UomDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'forwarder-booking',
      name: 'ForwarderBooking',
      component: ForwarderBooking,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'forwarder-booking/new',
      name: 'ForwarderBookingCreate',
      component: ForwarderBookingUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'forwarder-booking/:forwarderBookingId/edit',
      name: 'ForwarderBookingEdit',
      component: ForwarderBookingUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'forwarder-booking/:forwarderBookingId/view',
      name: 'ForwarderBookingView',
      component: ForwarderBookingDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-taxmonomy',
      name: 'ProductTaxmonomy',
      component: ProductTaxmonomy,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-taxmonomy/new',
      name: 'ProductTaxmonomyCreate',
      component: ProductTaxmonomyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-taxmonomy/:productTaxmonomyId/edit',
      name: 'ProductTaxmonomyEdit',
      component: ProductTaxmonomyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'product-taxmonomy/:productTaxmonomyId/view',
      name: 'ProductTaxmonomyView',
      component: ProductTaxmonomyDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'commercial-invoice',
      name: 'CommercialInvoice',
      component: CommercialInvoice,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'commercial-invoice/new',
      name: 'CommercialInvoiceCreate',
      component: CommercialInvoiceUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'commercial-invoice/:commercialInvoiceId/edit',
      name: 'CommercialInvoiceEdit',
      component: CommercialInvoiceUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'commercial-invoice/:commercialInvoiceId/view',
      name: 'CommercialInvoiceView',
      component: CommercialInvoiceDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'forwarder',
      name: 'Forwarder',
      component: Forwarder,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'forwarder/new',
      name: 'ForwarderCreate',
      component: ForwarderUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'forwarder/:forwarderId/edit',
      name: 'ForwarderEdit',
      component: ForwarderUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'forwarder/:forwarderId/view',
      name: 'ForwarderView',
      component: ForwarderDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'inspection',
      name: 'Inspection',
      component: Inspection,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'inspection/new',
      name: 'InspectionCreate',
      component: InspectionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'inspection/:inspectionId/edit',
      name: 'InspectionEdit',
      component: InspectionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'inspection/:inspectionId/view',
      name: 'InspectionView',
      component: InspectionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'operation-site',
      name: 'OperationSite',
      component: OperationSite,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'operation-site/new',
      name: 'OperationSiteCreate',
      component: OperationSiteUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'operation-site/:operationSiteId/edit',
      name: 'OperationSiteEdit',
      component: OperationSiteUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'operation-site/:operationSiteId/view',
      name: 'OperationSiteView',
      component: OperationSiteDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'group',
      name: 'Group',
      component: Group,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'group/new',
      name: 'GroupCreate',
      component: GroupUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'group/:groupId/edit',
      name: 'GroupEdit',
      component: GroupUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'group/:groupId/view',
      name: 'GroupView',
      component: GroupDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sales-delivery',
      name: 'SalesDelivery',
      component: SalesDelivery,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sales-delivery/new',
      name: 'SalesDeliveryCreate',
      component: SalesDeliveryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sales-delivery/:salesDeliveryId/edit',
      name: 'SalesDeliveryEdit',
      component: SalesDeliveryUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sales-delivery/:salesDeliveryId/view',
      name: 'SalesDeliveryView',
      component: SalesDeliveryDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client',
      name: 'Client',
      component: Client,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/new',
      name: 'ClientCreate',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/edit',
      name: 'ClientEdit',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/view',
      name: 'ClientView',
      component: ClientDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monday-column',
      name: 'MondayColumn',
      component: MondayColumn,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monday-column/new',
      name: 'MondayColumnCreate',
      component: MondayColumnUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monday-column/:mondayColumnId/edit',
      name: 'MondayColumnEdit',
      component: MondayColumnUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monday-column/:mondayColumnId/view',
      name: 'MondayColumnView',
      component: MondayColumnDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'authority',
      name: 'Authority',
      component: Authority,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'authority/new',
      name: 'AuthorityCreate',
      component: AuthorityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'authority/:authorityId/edit',
      name: 'AuthorityEdit',
      component: AuthorityUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'authority/:authorityId/view',
      name: 'AuthorityView',
      component: AuthorityDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
