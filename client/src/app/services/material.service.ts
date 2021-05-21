import {ElementRef} from '@angular/core';

declare var M: any;

export interface MaterialInstance {
  open?(): void;

  close?(): void;

  destroy?(): void;
}
export interface MaterialDatepicker extends MaterialInstance {
  date?: Date;
}

export class MaterialService {
  static toast(message: string): void {
    M.toast({html: message});
  }

  static initializeFloatingButton(ref: ElementRef<any> | undefined): void {
    // @ts-ignore
    M.FloatingActionButton.init(ref.nativeElement);
  }

  static updateTextInputs(): void {
    M.updateTextFields();
  }

  static initModal(ref: ElementRef): MaterialInstance {
    return M.Modal.init(ref.nativeElement);
  }

  static initTooltip(ref: ElementRef): MaterialInstance {
    return M.Tooltip.init(ref.nativeElement);
  }
  static initDatepicker(ref: ElementRef, onClose: () => void): MaterialDatepicker {
    return M.Datepicker.init(ref.nativeElement, {
      format: 'dd.mm.yyyy',
      showClearBtn: true,
      onClose
    });
  }
}
