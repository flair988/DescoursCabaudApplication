import { defineComponent, provide } from 'vue';

import UserService from '@/entities/user/user.service';
import OPSiteQualificationService from './op-site-qualification/op-site-qualification.service';
import PIService from './pi/pi.service';
import MondayUserService from './monday-user/monday-user.service';
import ProductFinishedService from './product-finished/product-finished.service';
import SupplierService from './supplier/supplier.service';
import ItemService from './item/item.service';
import PurchaseReturnService from './purchase-return/purchase-return.service';
import ProcessLogService from './process-log/process-log.service';
import QuotationService from './quotation/quotation.service';
import OrderFollowUpService from './order-follow-up/order-follow-up.service';
import OperationSiteAuditingService from './operation-site-auditing/operation-site-auditing.service';
import SupplierQualificationService from './supplier-qualification/supplier-qualification.service';
import UomService from './uom/uom.service';
import ForwarderBookingService from './forwarder-booking/forwarder-booking.service';
import ProductTaxmonomyService from './product-taxmonomy/product-taxmonomy.service';
import CommercialInvoiceService from './commercial-invoice/commercial-invoice.service';
import ForwarderService from './forwarder/forwarder.service';
import InspectionService from './inspection/inspection.service';
import OperationSiteService from './operation-site/operation-site.service';
import GroupService from './group/group.service';
import SalesDeliveryService from './sales-delivery/sales-delivery.service';
import ClientService from './client/client.service';
import MondayColumnService from './monday-column/monday-column.service';
import AuthorityService from './authority/authority.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Entities',
  setup() {
    provide('userService', () => new UserService());
    provide('oPSiteQualificationService', () => new OPSiteQualificationService());
    provide('pIService', () => new PIService());
    provide('mondayUserService', () => new MondayUserService());
    provide('productFinishedService', () => new ProductFinishedService());
    provide('supplierService', () => new SupplierService());
    provide('itemService', () => new ItemService());
    provide('purchaseReturnService', () => new PurchaseReturnService());
    provide('processLogService', () => new ProcessLogService());
    provide('quotationService', () => new QuotationService());
    provide('orderFollowUpService', () => new OrderFollowUpService());
    provide('operationSiteAuditingService', () => new OperationSiteAuditingService());
    provide('supplierQualificationService', () => new SupplierQualificationService());
    provide('uomService', () => new UomService());
    provide('forwarderBookingService', () => new ForwarderBookingService());
    provide('productTaxmonomyService', () => new ProductTaxmonomyService());
    provide('commercialInvoiceService', () => new CommercialInvoiceService());
    provide('forwarderService', () => new ForwarderService());
    provide('inspectionService', () => new InspectionService());
    provide('operationSiteService', () => new OperationSiteService());
    provide('groupService', () => new GroupService());
    provide('salesDeliveryService', () => new SalesDeliveryService());
    provide('clientService', () => new ClientService());
    provide('mondayColumnService', () => new MondayColumnService());
    provide('authorityService', () => new AuthorityService());
    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
});
