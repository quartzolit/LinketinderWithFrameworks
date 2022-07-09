export class PeopleRegex{
    public nameRegex: RegExp = /[A-Za-zÀ-ú\s]{3,}/;
    public emailRegex: RegExp = /^[A-Za-z][A-Za-z0-9 \w\.]+@[A-Za-z0-9_\-.]{3,}\.[A-Za-z]{2,3}(\.[[A-Za-z]])?/;
    public cpfRegex: RegExp = /(^\d{3})\.(\d{3})\.(\d{3})\-(\d{2})$/;
    public cnpjRegex: RegExp = /\d{2}\.\d{3}\.\d{3}\/(0001|0002)\-\d{2}/;
    public cepRegex: RegExp = /(^(\d{2}\.\d{3})|(\d{5}))\-\d{3}$/;
    public passwordRegex: RegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/

}