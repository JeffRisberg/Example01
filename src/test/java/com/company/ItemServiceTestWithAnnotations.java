package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.company.models.Item;
import com.company.services.ItemService;
import com.company.stores.ItemStore;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@Slf4j
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ItemServiceTestWithAnnotations {

  @Mock private ItemStore itemStore;

  @InjectMocks private ItemService itemService;

  @BeforeEach
  public void setUp() throws Exception {
    Item mockedItem1 = new Item(1L, "Item 1", "Item 1 Desc", 2000);
    Item mockedItem2 = new Item(2L, "Item 2", "Item 2 Desc", 4000);
    List<Item> mockedItems = new ArrayList<>();
    mockedItems.add(mockedItem1);
    mockedItems.add(mockedItem2);

    when(itemStore.readAllItems()).thenReturn(mockedItems);

    when(itemStore.findById(1L)).thenReturn(mockedItem1);

    log.info("Set up Store and Service");
  }

  @Test
  public void getItemById() {
    //
    // When
    //
    Item result = itemService.getById(1L);

    //
    // Verify
    //
    verify(itemStore, times(1)).findById(1L);
    assertEquals(result.getName(), "Item 1");
  }

  @Test
  public void getItemNameUpperCase() {
    //
    // When
    //
    String result = itemService.getItemNameUpperCase(1L);

    //
    // Verify
    //
    verify(itemStore, times(1)).findById(1L);
    assertEquals(result, "ITEM 1");
  }

  @Test
  public void getAveragePrice() {
    //
    // When
    //
    int result = itemService.getAveragePrice();

    //
    // Verify
    //
    verify(itemStore, times(1)).readAllItems();
    assertEquals(3000, result);
  }
}
