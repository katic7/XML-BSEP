import { TestBed } from '@angular/core/testing';

import { AdditionalservicesService } from './additionalservices.service';

describe('AdditionalservicesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdditionalservicesService = TestBed.get(AdditionalservicesService);
    expect(service).toBeTruthy();
  });
});
