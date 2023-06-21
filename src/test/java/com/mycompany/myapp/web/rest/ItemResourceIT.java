package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Item;
import com.mycompany.myapp.repository.ItemRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ItemResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItemResourceIT {

    private static final String DEFAULT_ITEM_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_FRANCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_FRANCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_KINGDEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_KINGDEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODEAG = "AAAAAAAAAA";
    private static final String UPDATED_CODEAG = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNICAL_DOCUMENTS = "AAAAAAAAAA";
    private static final String UPDATED_TECHNICAL_DOCUMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_OPPORTUNITY_SHEET = "AAAAAAAAAA";
    private static final String UPDATED_OPPORTUNITY_SHEET = "BBBBBBBBBB";

    private static final String DEFAULT_PACKING_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PACKING_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SALE_PACKAGE_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_SALE_PACKAGE_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_CARTON_LENGTH_MILIMETER = "AAAAAAAAAA";
    private static final String UPDATED_CARTON_LENGTH_MILIMETER = "BBBBBBBBBB";

    private static final String DEFAULT_CARTON_HEIGHT_MILIMETER = "AAAAAAAAAA";
    private static final String UPDATED_CARTON_HEIGHT_MILIMETER = "BBBBBBBBBB";

    private static final String DEFAULT_BARCODE = "AAAAAAAAAA";
    private static final String UPDATED_BARCODE = "BBBBBBBBBB";

    private static final String DEFAULT_CARTON_WEIGHT_KG = "AAAAAAAAAA";
    private static final String UPDATED_CARTON_WEIGHT_KG = "BBBBBBBBBB";

    private static final String DEFAULT_CARTON_WEIGHT_GR = "AAAAAAAAAA";
    private static final String UPDATED_CARTON_WEIGHT_GR = "BBBBBBBBBB";

    private static final String DEFAULT_CARTON_WIDTH_MILIMETER = "AAAAAAAAAA";
    private static final String UPDATED_CARTON_WIDTH_MILIMETER = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES = "BBBBBBBBBB";

    private static final String DEFAULT_DRAWING = "AAAAAAAAAA";
    private static final String UPDATED_DRAWING = "BBBBBBBBBB";

    private static final String DEFAULT_USER_MANUAL = "AAAAAAAAAA";
    private static final String UPDATED_USER_MANUAL = "BBBBBBBBBB";

    private static final String DEFAULT_PALLET_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_PALLET_SIZE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_OF_MARKETING = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_MARKETING = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_PIC = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_PIC = "BBBBBBBBBB";

    private static final String DEFAULT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_TAXONOMY = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_TAXONOMY = "BBBBBBBBBB";

    private static final String DEFAULT_NET_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_NET_WEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_GROSS_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_GROSS_WEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_OF_WEIGHT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT_OF_WEIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_CARTON_VOLUME_MILIMETER = "AAAAAAAAAA";
    private static final String UPDATED_CARTON_VOLUME_MILIMETER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/items";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemMockMvc;

    private Item item;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Item createEntity(EntityManager em) {
        Item item = new Item()
            .itemStatus(DEFAULT_ITEM_STATUS)
            .itemFranceName(DEFAULT_ITEM_FRANCE_NAME)
            .kingdeeId(DEFAULT_KINGDEE_ID)
            .itemName(DEFAULT_ITEM_NAME)
            .codeag(DEFAULT_CODEAG)
            .technicalDocuments(DEFAULT_TECHNICAL_DOCUMENTS)
            .certification(DEFAULT_CERTIFICATION)
            .opportunitySheet(DEFAULT_OPPORTUNITY_SHEET)
            .packingType(DEFAULT_PACKING_TYPE)
            .salePackageImage(DEFAULT_SALE_PACKAGE_IMAGE)
            .cartonLengthMilimeter(DEFAULT_CARTON_LENGTH_MILIMETER)
            .cartonHeightMilimeter(DEFAULT_CARTON_HEIGHT_MILIMETER)
            .barcode(DEFAULT_BARCODE)
            .cartonWeightKg(DEFAULT_CARTON_WEIGHT_KG)
            .cartonWeightGr(DEFAULT_CARTON_WEIGHT_GR)
            .cartonWidthMilimeter(DEFAULT_CARTON_WIDTH_MILIMETER)
            .productDescriptionAndFonctionalities(DEFAULT_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES)
            .drawing(DEFAULT_DRAWING)
            .userManual(DEFAULT_USER_MANUAL)
            .palletSize(DEFAULT_PALLET_SIZE)
            .typeOfMarketing(DEFAULT_TYPE_OF_MARKETING)
            .productPic(DEFAULT_PRODUCT_PIC)
            .label(DEFAULT_LABEL)
            .comment(DEFAULT_COMMENT)
            .productTaxonomy(DEFAULT_PRODUCT_TAXONOMY)
            .netWeight(DEFAULT_NET_WEIGHT)
            .grossWeight(DEFAULT_GROSS_WEIGHT)
            .unitOfWeight(DEFAULT_UNIT_OF_WEIGHT)
            .cartonVolumeMilimeter(DEFAULT_CARTON_VOLUME_MILIMETER);
        return item;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Item createUpdatedEntity(EntityManager em) {
        Item item = new Item()
            .itemStatus(UPDATED_ITEM_STATUS)
            .itemFranceName(UPDATED_ITEM_FRANCE_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .itemName(UPDATED_ITEM_NAME)
            .codeag(UPDATED_CODEAG)
            .technicalDocuments(UPDATED_TECHNICAL_DOCUMENTS)
            .certification(UPDATED_CERTIFICATION)
            .opportunitySheet(UPDATED_OPPORTUNITY_SHEET)
            .packingType(UPDATED_PACKING_TYPE)
            .salePackageImage(UPDATED_SALE_PACKAGE_IMAGE)
            .cartonLengthMilimeter(UPDATED_CARTON_LENGTH_MILIMETER)
            .cartonHeightMilimeter(UPDATED_CARTON_HEIGHT_MILIMETER)
            .barcode(UPDATED_BARCODE)
            .cartonWeightKg(UPDATED_CARTON_WEIGHT_KG)
            .cartonWeightGr(UPDATED_CARTON_WEIGHT_GR)
            .cartonWidthMilimeter(UPDATED_CARTON_WIDTH_MILIMETER)
            .productDescriptionAndFonctionalities(UPDATED_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES)
            .drawing(UPDATED_DRAWING)
            .userManual(UPDATED_USER_MANUAL)
            .palletSize(UPDATED_PALLET_SIZE)
            .typeOfMarketing(UPDATED_TYPE_OF_MARKETING)
            .productPic(UPDATED_PRODUCT_PIC)
            .label(UPDATED_LABEL)
            .comment(UPDATED_COMMENT)
            .productTaxonomy(UPDATED_PRODUCT_TAXONOMY)
            .netWeight(UPDATED_NET_WEIGHT)
            .grossWeight(UPDATED_GROSS_WEIGHT)
            .unitOfWeight(UPDATED_UNIT_OF_WEIGHT)
            .cartonVolumeMilimeter(UPDATED_CARTON_VOLUME_MILIMETER);
        return item;
    }

    @BeforeEach
    public void initTest() {
        item = createEntity(em);
    }

    @Test
    @Transactional
    void createItem() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();
        // Create the Item
        restItemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isCreated());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate + 1);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getItemStatus()).isEqualTo(DEFAULT_ITEM_STATUS);
        assertThat(testItem.getItemFranceName()).isEqualTo(DEFAULT_ITEM_FRANCE_NAME);
        assertThat(testItem.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testItem.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItem.getCodeag()).isEqualTo(DEFAULT_CODEAG);
        assertThat(testItem.getTechnicalDocuments()).isEqualTo(DEFAULT_TECHNICAL_DOCUMENTS);
        assertThat(testItem.getCertification()).isEqualTo(DEFAULT_CERTIFICATION);
        assertThat(testItem.getOpportunitySheet()).isEqualTo(DEFAULT_OPPORTUNITY_SHEET);
        assertThat(testItem.getPackingType()).isEqualTo(DEFAULT_PACKING_TYPE);
        assertThat(testItem.getSalePackageImage()).isEqualTo(DEFAULT_SALE_PACKAGE_IMAGE);
        assertThat(testItem.getCartonLengthMilimeter()).isEqualTo(DEFAULT_CARTON_LENGTH_MILIMETER);
        assertThat(testItem.getCartonHeightMilimeter()).isEqualTo(DEFAULT_CARTON_HEIGHT_MILIMETER);
        assertThat(testItem.getBarcode()).isEqualTo(DEFAULT_BARCODE);
        assertThat(testItem.getCartonWeightKg()).isEqualTo(DEFAULT_CARTON_WEIGHT_KG);
        assertThat(testItem.getCartonWeightGr()).isEqualTo(DEFAULT_CARTON_WEIGHT_GR);
        assertThat(testItem.getCartonWidthMilimeter()).isEqualTo(DEFAULT_CARTON_WIDTH_MILIMETER);
        assertThat(testItem.getProductDescriptionAndFonctionalities()).isEqualTo(DEFAULT_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES);
        assertThat(testItem.getDrawing()).isEqualTo(DEFAULT_DRAWING);
        assertThat(testItem.getUserManual()).isEqualTo(DEFAULT_USER_MANUAL);
        assertThat(testItem.getPalletSize()).isEqualTo(DEFAULT_PALLET_SIZE);
        assertThat(testItem.getTypeOfMarketing()).isEqualTo(DEFAULT_TYPE_OF_MARKETING);
        assertThat(testItem.getProductPic()).isEqualTo(DEFAULT_PRODUCT_PIC);
        assertThat(testItem.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testItem.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testItem.getProductTaxonomy()).isEqualTo(DEFAULT_PRODUCT_TAXONOMY);
        assertThat(testItem.getNetWeight()).isEqualTo(DEFAULT_NET_WEIGHT);
        assertThat(testItem.getGrossWeight()).isEqualTo(DEFAULT_GROSS_WEIGHT);
        assertThat(testItem.getUnitOfWeight()).isEqualTo(DEFAULT_UNIT_OF_WEIGHT);
        assertThat(testItem.getCartonVolumeMilimeter()).isEqualTo(DEFAULT_CARTON_VOLUME_MILIMETER);
    }

    @Test
    @Transactional
    void createItemWithExistingId() throws Exception {
        // Create the Item with an existing ID
        item.setId(1L);

        int databaseSizeBeforeCreate = itemRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllItems() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        // Get all the itemList
        restItemMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(item.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemStatus").value(hasItem(DEFAULT_ITEM_STATUS)))
            .andExpect(jsonPath("$.[*].itemFranceName").value(hasItem(DEFAULT_ITEM_FRANCE_NAME)))
            .andExpect(jsonPath("$.[*].kingdeeId").value(hasItem(DEFAULT_KINGDEE_ID)))
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].codeag").value(hasItem(DEFAULT_CODEAG)))
            .andExpect(jsonPath("$.[*].technicalDocuments").value(hasItem(DEFAULT_TECHNICAL_DOCUMENTS)))
            .andExpect(jsonPath("$.[*].certification").value(hasItem(DEFAULT_CERTIFICATION)))
            .andExpect(jsonPath("$.[*].opportunitySheet").value(hasItem(DEFAULT_OPPORTUNITY_SHEET)))
            .andExpect(jsonPath("$.[*].packingType").value(hasItem(DEFAULT_PACKING_TYPE)))
            .andExpect(jsonPath("$.[*].salePackageImage").value(hasItem(DEFAULT_SALE_PACKAGE_IMAGE)))
            .andExpect(jsonPath("$.[*].cartonLengthMilimeter").value(hasItem(DEFAULT_CARTON_LENGTH_MILIMETER)))
            .andExpect(jsonPath("$.[*].cartonHeightMilimeter").value(hasItem(DEFAULT_CARTON_HEIGHT_MILIMETER)))
            .andExpect(jsonPath("$.[*].barcode").value(hasItem(DEFAULT_BARCODE)))
            .andExpect(jsonPath("$.[*].cartonWeightKg").value(hasItem(DEFAULT_CARTON_WEIGHT_KG)))
            .andExpect(jsonPath("$.[*].cartonWeightGr").value(hasItem(DEFAULT_CARTON_WEIGHT_GR)))
            .andExpect(jsonPath("$.[*].cartonWidthMilimeter").value(hasItem(DEFAULT_CARTON_WIDTH_MILIMETER)))
            .andExpect(
                jsonPath("$.[*].productDescriptionAndFonctionalities").value(hasItem(DEFAULT_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES))
            )
            .andExpect(jsonPath("$.[*].drawing").value(hasItem(DEFAULT_DRAWING)))
            .andExpect(jsonPath("$.[*].userManual").value(hasItem(DEFAULT_USER_MANUAL)))
            .andExpect(jsonPath("$.[*].palletSize").value(hasItem(DEFAULT_PALLET_SIZE)))
            .andExpect(jsonPath("$.[*].typeOfMarketing").value(hasItem(DEFAULT_TYPE_OF_MARKETING)))
            .andExpect(jsonPath("$.[*].productPic").value(hasItem(DEFAULT_PRODUCT_PIC)))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].productTaxonomy").value(hasItem(DEFAULT_PRODUCT_TAXONOMY)))
            .andExpect(jsonPath("$.[*].netWeight").value(hasItem(DEFAULT_NET_WEIGHT)))
            .andExpect(jsonPath("$.[*].grossWeight").value(hasItem(DEFAULT_GROSS_WEIGHT)))
            .andExpect(jsonPath("$.[*].unitOfWeight").value(hasItem(DEFAULT_UNIT_OF_WEIGHT)))
            .andExpect(jsonPath("$.[*].cartonVolumeMilimeter").value(hasItem(DEFAULT_CARTON_VOLUME_MILIMETER)));
    }

    @Test
    @Transactional
    void getItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        // Get the item
        restItemMockMvc
            .perform(get(ENTITY_API_URL_ID, item.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(item.getId().intValue()))
            .andExpect(jsonPath("$.itemStatus").value(DEFAULT_ITEM_STATUS))
            .andExpect(jsonPath("$.itemFranceName").value(DEFAULT_ITEM_FRANCE_NAME))
            .andExpect(jsonPath("$.kingdeeId").value(DEFAULT_KINGDEE_ID))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.codeag").value(DEFAULT_CODEAG))
            .andExpect(jsonPath("$.technicalDocuments").value(DEFAULT_TECHNICAL_DOCUMENTS))
            .andExpect(jsonPath("$.certification").value(DEFAULT_CERTIFICATION))
            .andExpect(jsonPath("$.opportunitySheet").value(DEFAULT_OPPORTUNITY_SHEET))
            .andExpect(jsonPath("$.packingType").value(DEFAULT_PACKING_TYPE))
            .andExpect(jsonPath("$.salePackageImage").value(DEFAULT_SALE_PACKAGE_IMAGE))
            .andExpect(jsonPath("$.cartonLengthMilimeter").value(DEFAULT_CARTON_LENGTH_MILIMETER))
            .andExpect(jsonPath("$.cartonHeightMilimeter").value(DEFAULT_CARTON_HEIGHT_MILIMETER))
            .andExpect(jsonPath("$.barcode").value(DEFAULT_BARCODE))
            .andExpect(jsonPath("$.cartonWeightKg").value(DEFAULT_CARTON_WEIGHT_KG))
            .andExpect(jsonPath("$.cartonWeightGr").value(DEFAULT_CARTON_WEIGHT_GR))
            .andExpect(jsonPath("$.cartonWidthMilimeter").value(DEFAULT_CARTON_WIDTH_MILIMETER))
            .andExpect(jsonPath("$.productDescriptionAndFonctionalities").value(DEFAULT_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES))
            .andExpect(jsonPath("$.drawing").value(DEFAULT_DRAWING))
            .andExpect(jsonPath("$.userManual").value(DEFAULT_USER_MANUAL))
            .andExpect(jsonPath("$.palletSize").value(DEFAULT_PALLET_SIZE))
            .andExpect(jsonPath("$.typeOfMarketing").value(DEFAULT_TYPE_OF_MARKETING))
            .andExpect(jsonPath("$.productPic").value(DEFAULT_PRODUCT_PIC))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.productTaxonomy").value(DEFAULT_PRODUCT_TAXONOMY))
            .andExpect(jsonPath("$.netWeight").value(DEFAULT_NET_WEIGHT))
            .andExpect(jsonPath("$.grossWeight").value(DEFAULT_GROSS_WEIGHT))
            .andExpect(jsonPath("$.unitOfWeight").value(DEFAULT_UNIT_OF_WEIGHT))
            .andExpect(jsonPath("$.cartonVolumeMilimeter").value(DEFAULT_CARTON_VOLUME_MILIMETER));
    }

    @Test
    @Transactional
    void getNonExistingItem() throws Exception {
        // Get the item
        restItemMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Update the item
        Item updatedItem = itemRepository.findById(item.getId()).get();
        // Disconnect from session so that the updates on updatedItem are not directly saved in db
        em.detach(updatedItem);
        updatedItem
            .itemStatus(UPDATED_ITEM_STATUS)
            .itemFranceName(UPDATED_ITEM_FRANCE_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .itemName(UPDATED_ITEM_NAME)
            .codeag(UPDATED_CODEAG)
            .technicalDocuments(UPDATED_TECHNICAL_DOCUMENTS)
            .certification(UPDATED_CERTIFICATION)
            .opportunitySheet(UPDATED_OPPORTUNITY_SHEET)
            .packingType(UPDATED_PACKING_TYPE)
            .salePackageImage(UPDATED_SALE_PACKAGE_IMAGE)
            .cartonLengthMilimeter(UPDATED_CARTON_LENGTH_MILIMETER)
            .cartonHeightMilimeter(UPDATED_CARTON_HEIGHT_MILIMETER)
            .barcode(UPDATED_BARCODE)
            .cartonWeightKg(UPDATED_CARTON_WEIGHT_KG)
            .cartonWeightGr(UPDATED_CARTON_WEIGHT_GR)
            .cartonWidthMilimeter(UPDATED_CARTON_WIDTH_MILIMETER)
            .productDescriptionAndFonctionalities(UPDATED_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES)
            .drawing(UPDATED_DRAWING)
            .userManual(UPDATED_USER_MANUAL)
            .palletSize(UPDATED_PALLET_SIZE)
            .typeOfMarketing(UPDATED_TYPE_OF_MARKETING)
            .productPic(UPDATED_PRODUCT_PIC)
            .label(UPDATED_LABEL)
            .comment(UPDATED_COMMENT)
            .productTaxonomy(UPDATED_PRODUCT_TAXONOMY)
            .netWeight(UPDATED_NET_WEIGHT)
            .grossWeight(UPDATED_GROSS_WEIGHT)
            .unitOfWeight(UPDATED_UNIT_OF_WEIGHT)
            .cartonVolumeMilimeter(UPDATED_CARTON_VOLUME_MILIMETER);

        restItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedItem.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedItem))
            )
            .andExpect(status().isOk());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getItemStatus()).isEqualTo(UPDATED_ITEM_STATUS);
        assertThat(testItem.getItemFranceName()).isEqualTo(UPDATED_ITEM_FRANCE_NAME);
        assertThat(testItem.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testItem.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItem.getCodeag()).isEqualTo(UPDATED_CODEAG);
        assertThat(testItem.getTechnicalDocuments()).isEqualTo(UPDATED_TECHNICAL_DOCUMENTS);
        assertThat(testItem.getCertification()).isEqualTo(UPDATED_CERTIFICATION);
        assertThat(testItem.getOpportunitySheet()).isEqualTo(UPDATED_OPPORTUNITY_SHEET);
        assertThat(testItem.getPackingType()).isEqualTo(UPDATED_PACKING_TYPE);
        assertThat(testItem.getSalePackageImage()).isEqualTo(UPDATED_SALE_PACKAGE_IMAGE);
        assertThat(testItem.getCartonLengthMilimeter()).isEqualTo(UPDATED_CARTON_LENGTH_MILIMETER);
        assertThat(testItem.getCartonHeightMilimeter()).isEqualTo(UPDATED_CARTON_HEIGHT_MILIMETER);
        assertThat(testItem.getBarcode()).isEqualTo(UPDATED_BARCODE);
        assertThat(testItem.getCartonWeightKg()).isEqualTo(UPDATED_CARTON_WEIGHT_KG);
        assertThat(testItem.getCartonWeightGr()).isEqualTo(UPDATED_CARTON_WEIGHT_GR);
        assertThat(testItem.getCartonWidthMilimeter()).isEqualTo(UPDATED_CARTON_WIDTH_MILIMETER);
        assertThat(testItem.getProductDescriptionAndFonctionalities()).isEqualTo(UPDATED_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES);
        assertThat(testItem.getDrawing()).isEqualTo(UPDATED_DRAWING);
        assertThat(testItem.getUserManual()).isEqualTo(UPDATED_USER_MANUAL);
        assertThat(testItem.getPalletSize()).isEqualTo(UPDATED_PALLET_SIZE);
        assertThat(testItem.getTypeOfMarketing()).isEqualTo(UPDATED_TYPE_OF_MARKETING);
        assertThat(testItem.getProductPic()).isEqualTo(UPDATED_PRODUCT_PIC);
        assertThat(testItem.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testItem.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testItem.getProductTaxonomy()).isEqualTo(UPDATED_PRODUCT_TAXONOMY);
        assertThat(testItem.getNetWeight()).isEqualTo(UPDATED_NET_WEIGHT);
        assertThat(testItem.getGrossWeight()).isEqualTo(UPDATED_GROSS_WEIGHT);
        assertThat(testItem.getUnitOfWeight()).isEqualTo(UPDATED_UNIT_OF_WEIGHT);
        assertThat(testItem.getCartonVolumeMilimeter()).isEqualTo(UPDATED_CARTON_VOLUME_MILIMETER);
    }

    @Test
    @Transactional
    void putNonExistingItem() throws Exception {
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();
        item.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, item.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(item))
            )
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchItem() throws Exception {
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();
        item.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(item))
            )
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamItem() throws Exception {
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();
        item.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateItemWithPatch() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Update the item using partial update
        Item partialUpdatedItem = new Item();
        partialUpdatedItem.setId(item.getId());

        partialUpdatedItem
            .itemFranceName(UPDATED_ITEM_FRANCE_NAME)
            .itemName(UPDATED_ITEM_NAME)
            .technicalDocuments(UPDATED_TECHNICAL_DOCUMENTS)
            .certification(UPDATED_CERTIFICATION)
            .packingType(UPDATED_PACKING_TYPE)
            .cartonLengthMilimeter(UPDATED_CARTON_LENGTH_MILIMETER)
            .cartonHeightMilimeter(UPDATED_CARTON_HEIGHT_MILIMETER)
            .barcode(UPDATED_BARCODE)
            .cartonWeightKg(UPDATED_CARTON_WEIGHT_KG)
            .productDescriptionAndFonctionalities(UPDATED_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES)
            .userManual(UPDATED_USER_MANUAL)
            .typeOfMarketing(UPDATED_TYPE_OF_MARKETING)
            .productPic(UPDATED_PRODUCT_PIC)
            .label(UPDATED_LABEL)
            .productTaxonomy(UPDATED_PRODUCT_TAXONOMY)
            .netWeight(UPDATED_NET_WEIGHT)
            .unitOfWeight(UPDATED_UNIT_OF_WEIGHT);

        restItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItem))
            )
            .andExpect(status().isOk());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getItemStatus()).isEqualTo(DEFAULT_ITEM_STATUS);
        assertThat(testItem.getItemFranceName()).isEqualTo(UPDATED_ITEM_FRANCE_NAME);
        assertThat(testItem.getKingdeeId()).isEqualTo(DEFAULT_KINGDEE_ID);
        assertThat(testItem.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItem.getCodeag()).isEqualTo(DEFAULT_CODEAG);
        assertThat(testItem.getTechnicalDocuments()).isEqualTo(UPDATED_TECHNICAL_DOCUMENTS);
        assertThat(testItem.getCertification()).isEqualTo(UPDATED_CERTIFICATION);
        assertThat(testItem.getOpportunitySheet()).isEqualTo(DEFAULT_OPPORTUNITY_SHEET);
        assertThat(testItem.getPackingType()).isEqualTo(UPDATED_PACKING_TYPE);
        assertThat(testItem.getSalePackageImage()).isEqualTo(DEFAULT_SALE_PACKAGE_IMAGE);
        assertThat(testItem.getCartonLengthMilimeter()).isEqualTo(UPDATED_CARTON_LENGTH_MILIMETER);
        assertThat(testItem.getCartonHeightMilimeter()).isEqualTo(UPDATED_CARTON_HEIGHT_MILIMETER);
        assertThat(testItem.getBarcode()).isEqualTo(UPDATED_BARCODE);
        assertThat(testItem.getCartonWeightKg()).isEqualTo(UPDATED_CARTON_WEIGHT_KG);
        assertThat(testItem.getCartonWeightGr()).isEqualTo(DEFAULT_CARTON_WEIGHT_GR);
        assertThat(testItem.getCartonWidthMilimeter()).isEqualTo(DEFAULT_CARTON_WIDTH_MILIMETER);
        assertThat(testItem.getProductDescriptionAndFonctionalities()).isEqualTo(UPDATED_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES);
        assertThat(testItem.getDrawing()).isEqualTo(DEFAULT_DRAWING);
        assertThat(testItem.getUserManual()).isEqualTo(UPDATED_USER_MANUAL);
        assertThat(testItem.getPalletSize()).isEqualTo(DEFAULT_PALLET_SIZE);
        assertThat(testItem.getTypeOfMarketing()).isEqualTo(UPDATED_TYPE_OF_MARKETING);
        assertThat(testItem.getProductPic()).isEqualTo(UPDATED_PRODUCT_PIC);
        assertThat(testItem.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testItem.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testItem.getProductTaxonomy()).isEqualTo(UPDATED_PRODUCT_TAXONOMY);
        assertThat(testItem.getNetWeight()).isEqualTo(UPDATED_NET_WEIGHT);
        assertThat(testItem.getGrossWeight()).isEqualTo(DEFAULT_GROSS_WEIGHT);
        assertThat(testItem.getUnitOfWeight()).isEqualTo(UPDATED_UNIT_OF_WEIGHT);
        assertThat(testItem.getCartonVolumeMilimeter()).isEqualTo(DEFAULT_CARTON_VOLUME_MILIMETER);
    }

    @Test
    @Transactional
    void fullUpdateItemWithPatch() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Update the item using partial update
        Item partialUpdatedItem = new Item();
        partialUpdatedItem.setId(item.getId());

        partialUpdatedItem
            .itemStatus(UPDATED_ITEM_STATUS)
            .itemFranceName(UPDATED_ITEM_FRANCE_NAME)
            .kingdeeId(UPDATED_KINGDEE_ID)
            .itemName(UPDATED_ITEM_NAME)
            .codeag(UPDATED_CODEAG)
            .technicalDocuments(UPDATED_TECHNICAL_DOCUMENTS)
            .certification(UPDATED_CERTIFICATION)
            .opportunitySheet(UPDATED_OPPORTUNITY_SHEET)
            .packingType(UPDATED_PACKING_TYPE)
            .salePackageImage(UPDATED_SALE_PACKAGE_IMAGE)
            .cartonLengthMilimeter(UPDATED_CARTON_LENGTH_MILIMETER)
            .cartonHeightMilimeter(UPDATED_CARTON_HEIGHT_MILIMETER)
            .barcode(UPDATED_BARCODE)
            .cartonWeightKg(UPDATED_CARTON_WEIGHT_KG)
            .cartonWeightGr(UPDATED_CARTON_WEIGHT_GR)
            .cartonWidthMilimeter(UPDATED_CARTON_WIDTH_MILIMETER)
            .productDescriptionAndFonctionalities(UPDATED_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES)
            .drawing(UPDATED_DRAWING)
            .userManual(UPDATED_USER_MANUAL)
            .palletSize(UPDATED_PALLET_SIZE)
            .typeOfMarketing(UPDATED_TYPE_OF_MARKETING)
            .productPic(UPDATED_PRODUCT_PIC)
            .label(UPDATED_LABEL)
            .comment(UPDATED_COMMENT)
            .productTaxonomy(UPDATED_PRODUCT_TAXONOMY)
            .netWeight(UPDATED_NET_WEIGHT)
            .grossWeight(UPDATED_GROSS_WEIGHT)
            .unitOfWeight(UPDATED_UNIT_OF_WEIGHT)
            .cartonVolumeMilimeter(UPDATED_CARTON_VOLUME_MILIMETER);

        restItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItem.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItem))
            )
            .andExpect(status().isOk());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
        Item testItem = itemList.get(itemList.size() - 1);
        assertThat(testItem.getItemStatus()).isEqualTo(UPDATED_ITEM_STATUS);
        assertThat(testItem.getItemFranceName()).isEqualTo(UPDATED_ITEM_FRANCE_NAME);
        assertThat(testItem.getKingdeeId()).isEqualTo(UPDATED_KINGDEE_ID);
        assertThat(testItem.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItem.getCodeag()).isEqualTo(UPDATED_CODEAG);
        assertThat(testItem.getTechnicalDocuments()).isEqualTo(UPDATED_TECHNICAL_DOCUMENTS);
        assertThat(testItem.getCertification()).isEqualTo(UPDATED_CERTIFICATION);
        assertThat(testItem.getOpportunitySheet()).isEqualTo(UPDATED_OPPORTUNITY_SHEET);
        assertThat(testItem.getPackingType()).isEqualTo(UPDATED_PACKING_TYPE);
        assertThat(testItem.getSalePackageImage()).isEqualTo(UPDATED_SALE_PACKAGE_IMAGE);
        assertThat(testItem.getCartonLengthMilimeter()).isEqualTo(UPDATED_CARTON_LENGTH_MILIMETER);
        assertThat(testItem.getCartonHeightMilimeter()).isEqualTo(UPDATED_CARTON_HEIGHT_MILIMETER);
        assertThat(testItem.getBarcode()).isEqualTo(UPDATED_BARCODE);
        assertThat(testItem.getCartonWeightKg()).isEqualTo(UPDATED_CARTON_WEIGHT_KG);
        assertThat(testItem.getCartonWeightGr()).isEqualTo(UPDATED_CARTON_WEIGHT_GR);
        assertThat(testItem.getCartonWidthMilimeter()).isEqualTo(UPDATED_CARTON_WIDTH_MILIMETER);
        assertThat(testItem.getProductDescriptionAndFonctionalities()).isEqualTo(UPDATED_PRODUCT_DESCRIPTION_AND_FONCTIONALITIES);
        assertThat(testItem.getDrawing()).isEqualTo(UPDATED_DRAWING);
        assertThat(testItem.getUserManual()).isEqualTo(UPDATED_USER_MANUAL);
        assertThat(testItem.getPalletSize()).isEqualTo(UPDATED_PALLET_SIZE);
        assertThat(testItem.getTypeOfMarketing()).isEqualTo(UPDATED_TYPE_OF_MARKETING);
        assertThat(testItem.getProductPic()).isEqualTo(UPDATED_PRODUCT_PIC);
        assertThat(testItem.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testItem.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testItem.getProductTaxonomy()).isEqualTo(UPDATED_PRODUCT_TAXONOMY);
        assertThat(testItem.getNetWeight()).isEqualTo(UPDATED_NET_WEIGHT);
        assertThat(testItem.getGrossWeight()).isEqualTo(UPDATED_GROSS_WEIGHT);
        assertThat(testItem.getUnitOfWeight()).isEqualTo(UPDATED_UNIT_OF_WEIGHT);
        assertThat(testItem.getCartonVolumeMilimeter()).isEqualTo(UPDATED_CARTON_VOLUME_MILIMETER);
    }

    @Test
    @Transactional
    void patchNonExistingItem() throws Exception {
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();
        item.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, item.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(item))
            )
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchItem() throws Exception {
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();
        item.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(item))
            )
            .andExpect(status().isBadRequest());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamItem() throws Exception {
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();
        item.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItemMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(item)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Item in the database
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteItem() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);

        int databaseSizeBeforeDelete = itemRepository.findAll().size();

        // Delete the item
        restItemMockMvc
            .perform(delete(ENTITY_API_URL_ID, item.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
